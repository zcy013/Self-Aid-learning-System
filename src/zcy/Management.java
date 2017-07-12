package zcy;

import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.awt.event.*;
import java.sql.ResultSet;
import drager.HelloPage;
import java.sql.SQLException;
import drager.ChangeTPassword;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class Management extends JFrame {

	private static JPanel contentPane = new JPanel();
	private static Management frame;

	private static JList<String> list;
	private static int stuID;
	private static JTextArea info;
	private static JButton readBtn;

	private static JComboBox<String> selectChapter;
	private static JButton viewBtn, addBtn, delBtn, editBtn;
	private JTable table;
	private static DefaultTableModel tmodel;

	// // 整合时直接删去主方法（注意uimanager必须放在程序主方法中才起作用）
	// public static void main(String[] args) {
	// SwingUtilities.updateComponentTreeUI(contentPane);
	// UIManager.put("TabbedPane.font", new Font("微软雅黑", Font.PLAIN, 20));
	// UIManager.put("Label.font", new Font("微软雅黑", Font.PLAIN, 18));
	// UIManager.put("Button.font", new Font("微软雅黑", Font.PLAIN, 18));
	// UIManager.put("ComboBox.font", new Font("微软雅黑", Font.PLAIN, 18));
	// UIManager.put("TextField.font", new Font("微软雅黑", Font.PLAIN, 18));
	// UIManager.put("List.font", new Font("微软雅黑", Font.PLAIN, 16));
	// UIManager.put("TextArea.font", new Font("微软雅黑", Font.PLAIN, 16));
	// UIManager.put("Table.font", new Font("微软雅黑", Font.PLAIN, 16));
	// UIManager.put("OptionPane.font", new Font("微软雅黑", Font.PLAIN, 15));
	// run();
	// }

	public static void run() {
		frame = new Management();
		frame.setVisible(true);
	}

	public Management() {
		super("Java自助学习系统：教师管理");
		setSize(750, 900);
		setLocationRelativeTo(null);// 使窗口位于屏幕中央
		setMinimumSize(new Dimension(580, 750));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel manageAccount = new JPanel();
		tabbedPane.addTab("账户管理", null, manageAccount, null);
		manageAccount.setLayout(null);

		JPanel panel = new JPanel();// 放置功能按键
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, null, null, null));
		panel.setBounds(getWidth() / 2 - 110, getHeight() / 2 - 220, 220, 300);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		manageAccount.add(panel);
		manageAccount.addComponentListener(new ComponentAdapter() {// 窗口大小改变时组件（位置）相应变化
			@Override
			public void componentResized(ComponentEvent arg0) {
				panel.setBounds(getWidth() / 2 - 110, getHeight() / 2 - 220, 220, 300);
			}
		});

		// 账户管理界面
		panel.add(Box.createVerticalGlue());

		JButton editPswd = new JButton("修改密码");
		editPswd.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(editPswd);
		editPswd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ChangeTPassword cp = new ChangeTPassword();
			}
		});

		panel.add(Box.createVerticalStrut(20));

		JButton changeAccount = new JButton("切换账号");
		changeAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(changeAccount);
		changeAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				@SuppressWarnings("unused")
				HelloPage jHelloPage = new HelloPage();// 开始界面
			}
		});

		panel.add(Box.createVerticalStrut(20));

		JButton logout = new JButton("退出系统");
		logout.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(logout);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBUtility.closeConnection();
				dispose();// 关闭程序
			}
		});

		panel.add(Box.createVerticalGlue());

		// 学生信息界面
		JPanel studentInfo = new JPanel();
		studentInfo.setLayout(new BoxLayout(studentInfo, BoxLayout.X_AXIS));
		tabbedPane.addTab("学生信息", null, studentInfo, null);

		studentInfo.add(Box.createHorizontalStrut(20));

		// 已注册学生
		Box verticalBox_stu = Box.createVerticalBox();
		studentInfo.add(verticalBox_stu);

		verticalBox_stu.add(Box.createVerticalStrut(20));
		verticalBox_stu.add(Box.createHorizontalGlue());

		JLabel label_stu = new JLabel("已注册学生：");
		label_stu.setHorizontalAlignment(SwingConstants.CENTER);
		label_stu.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_stu.add(label_stu);

		JScrollPane stuScrollPane = new JScrollPane();
		verticalBox_stu.add(stuScrollPane);

		JPanel panel_stu = new JPanel();
		panel_stu.setBackground(Color.WHITE);
		stuScrollPane.setViewportView(panel_stu);

		list = new JList<String>();// 列表显示已注册学生
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		updateStu();
		panel_stu.add(list);

		JLabel header = new JLabel("    学号          姓名");// 学生信息列表表头
		header.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		header.setBackground(Color.WHITE);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		stuScrollPane.setColumnHeaderView(header);

		verticalBox_stu.add(Box.createVerticalStrut(20));
		verticalBox_stu.add(Box.createVerticalStrut(20));
		studentInfo.add(Box.createHorizontalStrut(20));

		Box verticalBox_info = Box.createVerticalBox();
		studentInfo.add(verticalBox_info);

		verticalBox_info.add(Box.createVerticalStrut(20));
		verticalBox_info.add(Box.createHorizontalGlue());

		JLabel label_info = new JLabel("学生详细信息：");
		label_info.setHorizontalAlignment(SwingConstants.CENTER);
		label_info.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_info.add(label_info);

		JScrollPane infoScrollPane = new JScrollPane();
		verticalBox_info.add(infoScrollPane);

		info = new JTextArea();
		info.setEditable(false);
		infoScrollPane.setViewportView(info);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				updateInfo();// 在list中选中学生，info中即显示相应信息
			}
		});

		readBtn = new JButton("批阅");
		readBtn.setEnabled(false);// 初始未选中学生，默认按钮不可用
		readBtn.setPreferredSize(new Dimension(70, 25));
		readBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_info.add(readBtn);
		readBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadAnswer.showPage(stuID);// 阅读答卷窗口
			}
		});

		Box btnhBox1 = Box.createHorizontalBox();
		verticalBox_info.add(btnhBox1);

		btnhBox1.add(Box.createHorizontalGlue());

		JButton updateBtn1 = new JButton("更新数据");
		updateBtn1.setBorder(new LineBorder(new Color(0, 0, 0)));
		updateBtn1.setContentAreaFilled(false);
		updateBtn1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 鼠标经过时外观改变
		updateBtn1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		updateBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = list.getSelectedIndex();
				updateStu();
				list.setSelectedIndex(i);
				updateInfo();
			}
		});
		btnhBox1.add(updateBtn1);

		studentInfo.add(Box.createHorizontalStrut(20));

		// 题库管理界面
		JPanel manageQuestions = new JPanel();
		manageQuestions.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("题库管理", null, manageQuestions, null);

		Box verticalBox = Box.createVerticalBox();
		manageQuestions.add(verticalBox, BorderLayout.CENTER);

		Box horizontalBox = Box.createHorizontalBox();// 放置章节选择和功能按钮
		horizontalBox.setPreferredSize(new Dimension(2147483647, 25));
		verticalBox.add(horizontalBox);

		selectChapter = new JComboBox<String>();
		selectChapter.setMinimumSize(new Dimension(37, 25));
		String[] chapters = new String[2];
		for (int i = 0; i < 2; i++)
			chapters[i] = "第" + (i + 2) + "章";
		selectChapter.setModel(new DefaultComboBoxModel<String>(chapters));
		selectChapter.setSelectedIndex(0);
		selectChapter.setMaximumSize(new Dimension(80, 25));
		selectChapter.setAlignmentX(Component.LEFT_ALIGNMENT);
		horizontalBox.add(selectChapter);

		Box btns = Box.createHorizontalBox();

		btns.setAlignmentX(Component.RIGHT_ALIGNMENT);
		horizontalBox.add(btns);

		btns.add(Box.createVerticalStrut(20));
		btns.add(Box.createHorizontalGlue());

		Handler handler = new Handler();

		viewBtn = new JButton("查看");
		viewBtn.setPreferredSize(new Dimension(70, 25));
		viewBtn.addActionListener(handler);
		btns.add(viewBtn);

		btns.add(Box.createHorizontalGlue());

		addBtn = new JButton("增加");
		addBtn.setPreferredSize(new Dimension(70, 25));
		addBtn.setEnabled(true);
		addBtn.addActionListener(handler);
		btns.add(addBtn);

		btns.add(Box.createHorizontalGlue());

		delBtn = new JButton("删除");
		delBtn.setPreferredSize(new Dimension(70, 25));
		delBtn.addActionListener(handler);
		btns.add(delBtn);

		btns.add(Box.createHorizontalGlue());

		editBtn = new JButton("修改");
		editBtn.setPreferredSize(new Dimension(70, 25));
		editBtn.addActionListener(handler);
		btns.add(editBtn);

		verticalBox.add(Box.createVerticalStrut(20));

		JScrollPane QAScrollPane = new JScrollPane();
		QAScrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		verticalBox.add(QAScrollPane);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;// 禁止编辑表格内容
			}
		};
		// 添加四列并设置表头
		tmodel = new DefaultTableModel();
		tmodel.addColumn("题号");
		tmodel.addColumn("类型");
		tmodel.addColumn("题目");
		tmodel.addColumn("答案");
		updateQuestion();

		table.setModel(tmodel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 17));
		table.setRowHeight(25);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);// 使表格列不可拖动

		// 设置表格初始列宽
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(390);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);

		// 设置部分列文字居中
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn("题号").setCellRenderer(render);
		table.getColumn("类型").setCellRenderer(render);
		table.getColumn("答案").setCellRenderer(render);
		QAScrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					viewBtn.setEnabled(true);
					delBtn.setEnabled(true);
					editBtn.setEnabled(true);
				}
			}
		});

		selectChapter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateQuestion();// 切换章节后更新表格数据
			}
		});

		manageQuestions.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		manageQuestions.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		manageQuestions.add(Box.createHorizontalStrut(20), BorderLayout.EAST);

		Box btnhBox2 = Box.createHorizontalBox();
		manageQuestions.add(btnhBox2, BorderLayout.SOUTH);

		btnhBox2.add(Box.createHorizontalGlue());

		JButton updateBtn2 = new JButton("更新数据");
		updateBtn2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		updateBtn2.setContentAreaFilled(false);
		updateBtn2.setBorder(new LineBorder(new Color(0, 0, 0)));
		updateBtn2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 鼠标移入时改变外观
		updateBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateQuestion();// 更新表格数据
			}
		});
		btnhBox2.add(updateBtn2);

		btnhBox2.add(Box.createHorizontalStrut(20));
	}

	class Handler implements ActionListener {// 功能按钮（增删改）的监听
		@Override
		public void actionPerformed(ActionEvent e) {
			int a = selectChapter.getSelectedIndex() + 2;// 章节数
			int b = 0;
			try {
				b = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
			} // 题号
			catch (Exception ex) {
			}
			if (e.getSource() == viewBtn)
				Question.view(a, b);// 查看题目
			else if (!canChange(a)) {// 本章题目不可修改
				JOptionPane.showMessageDialog(null, "本章已有学生提交答卷，不可再对题目进行修改！");
			} else {// 可修改
				if (e.getSource() == addBtn) // 点击增加按钮，则不需要选中题目
					Question.add(a);// 添加题目//不知为何经常自动添加“getContentPane().”，若因此报错，删去即可
				else if (e.getSource() == delBtn)
					Question.del(a, b);// 删除题目
				else if (e.getSource() == editBtn)
					Question.edit(a, b);// 编辑题目
			}
		}
	}

	protected static boolean canChange(int chapter) {// 本章题目是否可修改
		ResultSet rs = DBUtility.executeQuery("select * From students");
		try {
			while (rs.next()) {
				if (rs.getInt(1) == 11111111) // 跳过老师
					continue;
				if (rs.getString(chapter + 2).equals("unlearned"))
					continue;
				else {// 有学生本章学习状态非未学习，说明已提交答卷，则不可再修改本章题目
					DBUtility.closeConnection();
					return false;
				}
			}
			DBUtility.closeConnection();
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			DBUtility.closeConnection();
			return false;
		}
	}

	protected static boolean isUndone(int stuID, int chapter) {// 判断某学生某章节习题是否待批改
		ResultSet rs = DBUtility.executeQuery("select * From chapter" + chapter + "_scores Where Done=\"undone\"");// 获取所有此章节待批阅的学生
		try {
			while (rs.next()) {
				if (rs.getInt(1) == stuID) {
					DBUtility.closeConnection();
					return true;// 未批阅
				}
			}
		} catch (SQLException e) {
			DBUtility.closeConnection();
			e.printStackTrace();
		}
		DBUtility.closeConnection();
		return false;// 非待批阅
	}

	protected static boolean isUndone(int stuID) {// 判断某学生是否有待批阅的章节习题
		for (int chapter = 2; chapter <= 3; chapter++) {// 用遍历方便增加章节时修改代码
			boolean each = isUndone(stuID, chapter);// 分别判断每个章节是否待批阅
			if (each)
				return each;// 有未批阅的章节
		}
		return false;// 没有未批阅的章节

		// 另一种判断方式
		// for (int chapter = 2; chapter <= 3; chapter++) {
		// ResultSet rs = DBUtility.executeQuery("select * From students Where
		// StudentID =" + stuID);
		// try {
		// while (rs.next())
		// if (rs.getString(chapter + 2).equals("submitted")) // 已提交等价于待批阅
		// return true;
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		// return false;
	}

	// 代码待优化，由于学生未答完题目时数据库中done处参数为null，即既不是undone也不是done，故undone和done两状态需分别判断，也许存在更优逻辑
	protected static boolean isDone(int stuID) {// 判断某学生是否有批阅完的章节
		for (int chapter = 2; chapter <= 3; chapter++) {
			ResultSet rs = DBUtility.executeQuery("select * From students Where StudentID =" + stuID);
			try {
				while (rs.next())
					if (rs.getString(chapter + 2).equals("passed") || rs.getString(chapter + 2).equals("unpassed")) {
						DBUtility.closeConnection();
						return true;
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		DBUtility.closeConnection();
		return false;
	}

	protected static void updateStu() {// 更新学生列表数据
		DefaultListModel<String> lmodel = new DefaultListModel<String>();
		ResultSet lrs = DBUtility.executeQuery("select * From students");
		try {
			while (lrs.next()) {
				if (lrs.getInt(1) != 11111111) {// 11111111代表教师，不显示
					if (isUndone(lrs.getInt(1)))
						lmodel.addElement(lrs.getString(1) + "      " + lrs.getString(2) + "（待批阅）");// 提示有待批阅章节
					else
						lmodel.addElement(lrs.getString(1) + "      " + lrs.getString(2));
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		list.setModel(lmodel);
		DBUtility.closeConnection();
	}

	protected static void updateInfo() {// 更新学生详细信息
		int i = list.getSelectedIndex() + 1;// 所选学生在数据库中索引，因列表中不显示老师信息，故相比数据库学生索引差1
		ResultSet rs = DBUtility.executeQuery("select * From students limit " + i + ",1");
		try {
			String str = "";
			if (rs.next()) {
				stuID = rs.getInt(1);
				for (int k = 1; k <= 14; k++) {
					if (k == 3 || k == 13)
						continue;// 跳过学生密码和验证码
					str += rs.getMetaData().getColumnName(k) + "：" + rs.getString(k) + "\n";
				}
			}
			info.setText(str);
			info.setCaretPosition(0);// 光标移至最初
			readBtn.setEnabled(true);
			if (isUndone(stuID)) // 有章节未批阅
				readBtn.setText("批阅");
			else if (isDone(stuID)) // 有章节已批阅
				readBtn.setText("重新批阅");
			else {// 无已提交答卷或其他状态
				readBtn.setText("批阅");
				readBtn.setEnabled(false);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		DBUtility.closeConnection();
	}

	protected static void updateQuestion() {// 更新显示题目的表格数据
		for (int i = 0; i < tmodel.getRowCount();)// 清空原数据
			tmodel.removeRow(tmodel.getRowCount() - 1);
		int n = selectChapter.getSelectedIndex() + 2;
		if (n >= 2 && n <= 3) {
			ResultSet trs = DBUtility.executeQuery("select * From learning_system.chapter" + n + "_questions");
			try {
				while (trs.next()) {
					Vector<String> row = new Vector<String>();
					row.addElement(trs.getString(1));
					row.addElement(trs.getString(2));
					row.addElement(trs.getString(3));
					row.addElement(trs.getString(4));
					tmodel.addRow(row);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		viewBtn.setEnabled(false);
		delBtn.setEnabled(false);
		editBtn.setEnabled(false);
		DBUtility.closeConnection();
	}
}