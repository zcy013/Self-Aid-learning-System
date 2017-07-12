package zcy;

import java.awt.*;
import Qiang.find;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Question extends JFrame {
	static Question frame;

	public static void view(int chapter, int index) {// 查看题目
		frame = new Question(chapter, index, true, false, "view");
		frame.setVisible(true);
	}

	public static void add(int chapter) {// 添加题目
		frame = new Question(chapter, 0, false, true, "add");
		frame.setVisible(true);
	}

	public static void del(int chapter, int index) {// 删除题目
		frame = new Question(chapter, index, true, false, "del");
		frame.setVisible(true);
	}

	public static void edit(int chapter, int index) {// 编辑题目
		frame = new Question(chapter, index, true, true, "edit");
		frame.setVisible(true);
	}

	private String method;
	private int chapter;
	private int index;
	private ResultSet rs;
	private JPanel contentPane;
	private JTextField numbert;
	private JComboBox<Object> typeb;
	private JTextArea questionta;
	private JTextArea answerta;
	private JButton preBtn;
	private JButton nextBtn;
	private JButton OKBtn;
	private JButton cancelBtn;

	// filled表示是否显示内容（仅添加题目时不显示），editable表示是否可编辑
	public Question(int c, int i, boolean filled, boolean editable, String m) {
		chapter = c;// 当前在浏览的章节
		index = i;// 选中的题号
		method = m;// 调用窗口的方法名
		preBtn = new JButton("上一题");
		nextBtn = new JButton("下一题");
		
		switch (method) {
		case "view":
			setTitle("查看题目");
			break;
		case "add":
			setTitle("增加题目");
			break;
		case "del":
			setTitle("删除题目");
			break;
		case "edit":
			setTitle("修改题目");
			break;
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 600);
		setMinimumSize(new Dimension(250, 350));
		setLocationRelativeTo(null);// 使窗口位于屏幕中央

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		contentPane.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		contentPane.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		contentPane.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

		Box vbox = Box.createVerticalBox();// 放置关键控件
		contentPane.add(vbox, BorderLayout.CENTER);

		Box hBox = Box.createHorizontalBox();// 放置题号和类型
		hBox.setMaximumSize(new Dimension(2147483647, 25));
		vbox.add(hBox);

		JLabel numberl = new JLabel("题号");
		hBox.add(numberl);

		hBox.add(Box.createHorizontalStrut(20));

		numbert = new JTextField();
		numbert.setPreferredSize(new Dimension(60, 24));
		numbert.setMinimumSize(new Dimension(10, 24));
		numbert.setEditable(editable);
		numbert.setMaximumSize(new Dimension(100, 32767));
		numbert.setBounds(0, 0, 50, 25);
		hBox.add(numbert);

		hBox.add(Box.createHorizontalGlue());

		JLabel typel = new JLabel("类型");
		hBox.add(typel);

		hBox.add(Box.createHorizontalStrut(20));

		typeb = new JComboBox<Object>();
		typeb.setMaximumSize(new Dimension(100, 32767));
		typeb.setEnabled(editable);
		typeb.setPreferredSize(new Dimension(80, 24));
		typeb.setMinimumSize(new Dimension(10, 24));
		typeb.setModel(new DefaultComboBoxModel<Object>(new String[] { "choice", "blank" }));
		hBox.add(typeb);

		vbox.add(Box.createVerticalStrut(20));

		JScrollPane questionsp = new JScrollPane();
		questionsp.setPreferredSize(new Dimension(2, getHeight() / 2));
		vbox.add(questionsp);

		questionta = new JTextArea();
		questionta.setLineWrap(true);
		questionta.setEditable(editable);
		questionsp.setViewportView(questionta);

		JLabel questionl = new JLabel("题目");
		questionl.setHorizontalAlignment(SwingConstants.CENTER);
		questionsp.setColumnHeaderView(questionl);

		vbox.add(Box.createVerticalStrut(20));

		JScrollPane answersp = new JScrollPane();
		answersp.setPreferredSize(new Dimension(2, getHeight() / 2));
		vbox.add(answersp);

		answerta = new JTextArea();
		answerta.setEditable(editable);
		answersp.setViewportView(answerta);

		if (filled) // 判断是否需要显示内容（等价于是否点击了增加按钮）
			update();

		JLabel answerl = new JLabel("参考答案");
		answerl.setHorizontalAlignment(SwingConstants.CENTER);
		answersp.setColumnHeaderView(answerl);

		Box btns = Box.createHorizontalBox();// 功能按钮
		vbox.add(btns);

		btns.add(Box.createHorizontalGlue());
		btns.add(Box.createHorizontalGlue());

		changeIndexHandler handler = new changeIndexHandler();

		preBtn.setAlignmentY(TOP_ALIGNMENT);
		preBtn.addActionListener(handler);

		nextBtn.setAlignmentY(TOP_ALIGNMENT);
		nextBtn.addActionListener(handler);

		if (!method.equals("add")) {// 只有添加题目时不需要显示上下一题按钮
			btns.add(preBtn);
			btns.add(Box.createHorizontalGlue());
			btns.add(Box.createVerticalStrut(20));
			btns.add(nextBtn);
			btns.add(Box.createHorizontalGlue());
			btns.add(Box.createVerticalStrut(20));
		}

		JButton editBtn = new JButton("修改");// 查看题目时点击，则调用修改窗口
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Management.canChange(chapter))
					JOptionPane.showMessageDialog(null, "本章已有学生提交答卷，不可再对题目进行修改！");
				else {
					dispose();
					edit(chapter, index);
				}
			}
		});
		editBtn.setAlignmentY(TOP_ALIGNMENT);

		questionChangedHandler handler2 = new questionChangedHandler();

		OKBtn = new JButton("确认");
		OKBtn.setAlignmentY(TOP_ALIGNMENT);
		OKBtn.addActionListener(handler2);

		cancelBtn = new JButton("取消");
		cancelBtn.setAlignmentY(TOP_ALIGNMENT);
		cancelBtn.addActionListener(handler2);

		if (method.equals("view")) {// 查看题目的窗口添加编辑和确认按钮
			btns.add(editBtn);
			btns.add(Box.createHorizontalGlue());
			btns.add(Box.createVerticalStrut(20));
			btns.add(OKBtn);
		} else {// 其他窗口添加确认和取消按钮
			btns.add(OKBtn);
			btns.add(Box.createHorizontalGlue());
			btns.add(Box.createVerticalStrut(20));
			btns.add(cancelBtn);
		}

		btns.add(Box.createHorizontalGlue());
		btns.add(Box.createHorizontalGlue());
	}

	private boolean isChanged(int chapter, int index) {// 判断是否对题目进行了修改
		boolean result = false;// 默认未修改
		rs = DBUtility.executeQuery("select * From chapter" + chapter + "_questions Where QuestionID=" + index);
		try {
			if (rs.next()) {
				if (!numbert.getText().equals(rs.getString(1)) || !typeb.getSelectedItem().equals(rs.getString(2))
						|| !questionta.getText().equals(rs.getString(3)) || !answerta.getText().equals(rs.getString(4)))
					result = true;// 对比数据，有一项改变则题目改变
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}

	private boolean check() {// 检查输入是否合理
		try {
			int number = Integer.parseInt(numbert.getText());
			if (number < 1 || number > 20)
				return false;// 超出数据库预留题号范围
			if (method.equals("add") || (method.equals("edit") && number != index)) {// 增加题目时或修改题目且修改了题号时会检查
				ResultSet rs = DBUtility.executeQuery("select * From chapter" + chapter + "_questions");
				while (rs.next()) {
					if (number == rs.getInt(1))
						return false;// 题号与现有题号重复
				}
			}
		} catch (Exception e) {
			return false;// 题号非数字或其他错误
		}
		String type = typeb.getSelectedItem().toString();
		String question = questionta.getText();
		String answer = answerta.getText();
		if (numbert.getText().equals("") || question.equals("") || answer.equals(""))
			return false;// 有未填项
		if (type.equals("choice")
				&& !(answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D")))
			return false;// 选择题答案不是选项ABCD
		return true;
	}

	private void update() {// 题号改变时更新内容
		rs = DBUtility.executeQuery("select * From chapter" + chapter + "_questions Where QuestionID=" + index);
		try {
			if (rs.next()) {
				numbert.setText(rs.getString(1));
				typeb.setSelectedItem(rs.getString(2));
				questionta.setText(rs.getString(3));
				answerta.setText(rs.getString(4));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		questionta.setCaretPosition(0);
		answerta.setCaretPosition(0);// 光标移至最初

		try {
			ResultSet rs1 = DBUtility.executeQuery("select * From chapter" + chapter + "_questions limit 0,1");// 获取数据库中第一行数据
			rs1.next();
			if (rs1.getInt(1) == index)
				preBtn.setEnabled(false);// 浏览第一道题时上一题按钮不可用
			else
				preBtn.setEnabled(true);
			ResultSet rs2 = DBUtility.executeQuery(
					"select * From chapter" + chapter + "_questions limit " + (find.num2questNum(chapter) - 1) + ",1");// 获取数据库中最后一行数据
			rs2.next();
			if (rs2.getInt(1) == index)
				nextBtn.setEnabled(false);// 浏览最后一道题时下一题按钮不可用
			else
				nextBtn.setEnabled(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class changeIndexHandler implements ActionListener {// 用上下一题按钮切换题目的事件处理
		@Override
		public void actionPerformed(ActionEvent e) {
			ResultSet rs;
			try {
				do {
					if (e.getSource() == preBtn) {
						index--;
					} else if (e.getSource() == nextBtn) {
						index++;
					} // 题号变1
					rs = DBUtility
							.executeQuery("select * From chapter" + chapter + "_questions where QuestionID=" + index);
				} while (!rs.next());// 数据库中无该题号对应的题时题号继续自增或自减
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			update();
		}
	}

	class questionChangedHandler implements ActionListener {// 按钮增删改的事件处理
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean reread = false;// 题目是否改变，是否需要更新显示内容
			if (e.getSource() == OKBtn) {// 点击确认
				if (method.equals("del")) {// 删除题目
					int delORnot = JOptionPane.showConfirmDialog(null, "确认删除此题？此操作不可恢复", "", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if (delORnot == JOptionPane.YES_OPTION) {// 确认删除
						DBUtility.executeUpdate(
								"Delete from chapter" + chapter + "_questions where QuestionID=" + index);
						JOptionPane.showMessageDialog(null, "删除成功！");
						frame.dispose();
						reread = true;
					}
				} else if (!check()) // 输入不合法
					JOptionPane.showMessageDialog(null, "输入错误！");
				else if (method.equals("add")) {// 添加题目
					DBUtility.executeUpdate("Insert into chapter" + chapter
							+ "_questions(QuestionID,Property,Description,Reference) values(" + numbert.getText() + ",'"
							+ typeb.getSelectedItem() + "','" + questionta.getText() + "','" + answerta.getText()
							+ "')");
					JOptionPane.showMessageDialog(null, "添加成功！");
					frame.dispose();
					reread = true;
				} else if (method.equals("edit") && isChanged(chapter, index)) {// 更改题目且题目内容被编辑
					DBUtility.executeUpdate("Update chapter" + chapter + "_questions set QuestionID="
							+ numbert.getText() + ", Property='" + typeb.getSelectedItem() + "', Description='"
							+ questionta.getText() + "', Reference='" + answerta.getText() + "' where QuestionID="
							+ index);
					JOptionPane.showMessageDialog(null, "修改成功！");
					frame.dispose();
					reread = true;
				} else
					dispose();
			} else// 点击取消
				dispose();
			if (reread) {// 题目发生变化
				Management.updateQuestion();// 更新题目数据
			}
		}
	}
}