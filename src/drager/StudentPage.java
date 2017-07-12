package drager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Qiang.studyFace;

public class StudentPage extends JFrame {

	// 选择进入学习
	private JButton[] chapter;
	// 展示章节分数和学习状态
	private JLabel[] chapterInfo;
	// private JLabel portrait;//头像
	private JLabel name;// 姓名
	private String studentID;

	private JMenuBar bar;
	private JMenu option;
	private JMenuItem changePortrait;
	private JMenuItem changePassword;
	private JMenuItem changeName;
	private JMenuItem logout;

	private GridBagLayout layout;
	GridBagConstraints constraints;

	public StudentPage(String studentID) {
		super("Java自助学习系统");
		getContentPane().setBackground(HelloPage.myBlue);
		this.studentID = studentID;
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		setLayout(layout);

		name = new JLabel();
		name.setText(Login.getName(studentID));
		name.setAlignmentX(SwingConstants.LEFT);

		addComponent(name, 0, 0, 1, 2);

		bar = new JMenuBar();
		option = new JMenu("选项(O)");
		option.setMnemonic('o');
		changeName = new JMenuItem("修改姓名");
		changeName.setMnemonic('n');
		changePassword = new JMenuItem("修改密码");
		changePassword.setMnemonic('p');
		logout = new JMenuItem("退出登录");
		logout.setMnemonic('u');
		JMenuListener jMenuListener = new JMenuListener();
		changeName.addActionListener(jMenuListener);
		changePassword.addActionListener(jMenuListener);
		logout.addActionListener(jMenuListener);

		option.add(changeName);
		option.add(changePassword);
		option.addSeparator();
		option.add(logout);
		bar.add(option);
		setJMenuBar(bar);

		chapter = new JButton[9];
		JBotonListener jBotonListener = new JBotonListener();
		for (int i = 0; i < 9; i++) {
			chapter[i] = new JButton("Chapter " + (i + 2));
			chapter[i].addActionListener(jBotonListener);
			chapter[i].setBackground(HelloPage.myGray);
			addComponent(chapter[i], 0, i + 2, 1, 1);
		}

		chapterInfo = new JLabel[9];
		try {
			ResultSet rs = DBUtility.executeQuery("SELECT * From students WHERE StudentID=" + studentID);
			if (rs.next()) {
				for (int i = 0; i < 9; i++) {
					chapterInfo[i] = new JLabel(rs.getString(i + 4));
					addComponent(chapterInfo[i], 1, i + 2, 1, 1);
				}
				DBUtility.closeConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
			DBUtility.closeConnection();
		}

	}

	public static void showPage(String studentID) {
		StudentPage sFirstPage = new StudentPage(studentID);
		sFirstPage.setMinimumSize(new Dimension(350, 500));
		sFirstPage.setVisible(true);
		sFirstPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sFirstPage.setLocationRelativeTo(null);
	}

	private class JMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == changeName) {
				dispose();
				new ChangeName(studentID);
				return;

			} else if (e.getSource() == changePassword) {
				dispose();
				ChangePassword changePassword = new ChangePassword(studentID, name);
				return;
			} else if (e.getSource() == changePortrait) {

			} else if (e.getSource() == logout)
				dispose();
			HelloPage jHelloPage = new HelloPage();

		}

	}

	private class JBotonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 9; i++) {
				if (e.getSource() == chapter[i]) {
					studyFace frame;
					try {
						dispose();
						frame = new studyFace(i + 2, Integer.parseInt(studentID));
						frame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // 章节，学号
				}
			}

		}

	}

	private void addComponent(Component component, int column, int row, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridheight = height;
		constraints.gridwidth = width;
		layout.setConstraints(component, constraints);
		add(component);
	}
}
