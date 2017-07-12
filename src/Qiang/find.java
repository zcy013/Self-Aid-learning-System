package Qiang;

import java.sql.ResultSet;
import java.sql.SQLException;

public class find {
	// 获取ppt本地路径
	public static String num2URL(int chapterNum, int cPage) throws SQLException {
		ResultSet rs = DBUtility
				.executeQuery("SELECT PPT_page,chapter" + chapterNum + " From PPTs where PPT_page =" + cPage);
		rs.next();
		String path = rs.getString(2);
		DBUtility.closeConnection();
		return path;
	}

	// 获取ppt总页数
	public static String num2Chapter(int chapterNum) throws SQLException {
		ResultSet rs1 = DBUtility.executeQuery("select count(Chapter" + chapterNum + ") from  ppts");
		rs1.absolute(1);
		String num = rs1.getString(1);
		DBUtility.closeConnection();
		return num;
	}

	// 拓展阅读内容提取
	public static String num2txt(int chapterNum) throws SQLException {
		ResultSet rs = DBUtility.executeQuery("SELECT * From reading where chapterID =" + chapterNum);
		rs.next();
		String reading = rs.getString(3);
		DBUtility.closeConnection();
		return reading;
	}

	// 拓展阅读题目提取
	public static String num2title(int chapterNum) throws SQLException {
		ResultSet rs = DBUtility.executeQuery("SELECT * From reading where chapterID =" + chapterNum);
		rs.next();
		String reading = rs.getString(2);
		DBUtility.closeConnection();
		return reading;
	}

	// 计算题目总数
	public static int num2questNum(int chapterNum) throws SQLException {
		ResultSet rs1 = DBUtility.executeQuery("select count(QuestionID) from  chapter" + chapterNum + "_questions");
		rs1.absolute(1);
		int num = rs1.getInt(1);
		DBUtility.closeConnection();
		return num;
	}

	// 获取问题题干
	public static String num2question(int chapterNum, int questNum) throws SQLException {
		ResultSet rs = DBUtility
				.executeQuery("SELECT *" + " From Chapter" + chapterNum + "_Questions where QuestionID =" + questNum);
		rs.next();
		String description = rs.getString(3);
		DBUtility.closeConnection();
		return description;
	}

	// 获取题目属性（选择/填空）
	public static String num2questIdentity(int chapterNum, int questNum) throws SQLException {
		ResultSet rs = DBUtility
				.executeQuery("SELECT *" + " From Chapter" + chapterNum + "_Questions where QuestionID =" + questNum);
		rs.next();
		String identity = rs.getString(2);
		DBUtility.closeConnection();
		return identity;
	}

	// 获取题目参考答案
	public static String num2answer(int chapterNum, int questNum) throws SQLException {
		ResultSet rs = DBUtility
				.executeQuery("SELECT *" + " From Chapter" + chapterNum + "_Questions where QuestionID =" + questNum);
		rs.next();
		String answer = rs.getString(4);
		DBUtility.closeConnection();
		return answer;
	}

	// 获取学生回答
	public static String num2stuAnswer(int chapterNum, int questNum, int stuID) throws SQLException {
		ResultSet rs = DBUtility
				.executeQuery("SELECT *" + " From Chapter" + chapterNum + "_Answers where StudentID =" + stuID);
		rs.next();
		String answer = rs.getString("Answer" + questNum);
		DBUtility.closeConnection();
		return answer;
	}

	// 检查是否提交true为提交
	public static boolean num2done(int chapterNum, int stuID) throws SQLException {
		boolean submit = false;
		ResultSet rs = DBUtility
				.executeQuery("SELECT * From Students where StudentID =" + stuID);
		rs.next();
		String check = rs.getString("Chapter"+chapterNum);
		if (!check.equals("unlearned")) {
			submit = true;
		}
		DBUtility.closeConnection();
		return submit;
	}

}
