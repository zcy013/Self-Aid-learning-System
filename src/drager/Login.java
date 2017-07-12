package drager;

import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import drager.DBUtility;
import zcy.Management;

public class Login {

	private static int flag = 1;// 1表示学生，2表示老师
	private static String studentID;

	// 尝试登录
	public static boolean loginOK(String account, String password) {
		if (account.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入学号！");
			return false;
		}
		if (password.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入密码！");
			return false;
		}

		if (account.charAt(0) >= '0' && account.charAt(0) <= '9') {// 学生
			try {
				ResultSet rs = DBUtility
						.executeQuery("SELECT StudentPassword, Activation From students WHERE StudentID=" + account);
				if (rs.next()) {
					if (rs.getString(1).equals(password)) {
						if (rs.getString(2).equals("no")) {
							JOptionPane.showMessageDialog(null, "账号未激活，请点击找回密码！");
							DBUtility.closeConnection();
							return false;
						}

						flag = 1;
						studentID = account;
						DBUtility.closeConnection();
						return true;
					} else {
						JOptionPane.showMessageDialog(null, "密码错误！");
						DBUtility.closeConnection();
						return false;
					}

				}

				else {
					JOptionPane.showMessageDialog(null, "用户不存在！");
					DBUtility.closeConnection();
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				DBUtility.closeConnection();
				return false;
			}
		}

		if (account.equals("admin")) {
			try {
				ResultSet rs = DBUtility
						.executeQuery("SELECT StudentPassword From students WHERE StudentID=" + 11111111);
				if (rs.next()) {
					if (rs.getString(1).equals(password)) {
						flag = 2;// 老师
						DBUtility.closeConnection();
						return true;
					} else {
						JOptionPane.showMessageDialog(null, "密码错误！");
						DBUtility.closeConnection();
						return false;
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				DBUtility.closeConnection();
				return false;
			}
		}
		return false;

	}

	public static void nextPage() {
		if (flag == 1) {
			StudentPage.showPage(studentID);
		} else if (flag == 2) {
			Management.run();// 老师界面
		}

	}

	public static boolean changePassword(String newPassword) {

		return DBUtility.executeUpdate("UPDATE students SET StudentPassword = " + "\"" + newPassword + "\""
				+ " WHERE StudentID = " + studentID);
	}

	public static boolean changeName(String newName) {

		return DBUtility.executeUpdate(
				"UPDATE students SET StudentName = " + "\"" + newName + "\"" + " WHERE StudentID = " + studentID);
	}

	public static String getName(String studentID) {
		try {
			ResultSet rs = DBUtility.executeQuery("SELECT StudentName FROM students where StudentID = " + studentID);
			rs.next();
			String name = rs.getString(1);
			DBUtility.closeConnection();
			return name;

		} catch (Exception e) {
			e.printStackTrace();
			DBUtility.closeConnection();
			return null;
		}
	}
}
