package Qiang;

import java.sql.ResultSet;
import java.sql.SQLException;

public class counter {
	// 计算完成度false为未完成
	public static boolean checkFinished(int stuID, int chapterID) throws NumberFormatException, SQLException {
		boolean check = false;
		int total = find.num2questNum(chapterID);
		int finished = 0;
		ResultSet rSet = DBUtility
				.executeQuery("SELECT * from chapter" + chapterID + "_Answers where StudentID=" + stuID);
		rSet.next();
		for (int i = 1; i <= total; i++) {
			if (find.num2stuAnswer(chapterID, i, stuID) != null) {
				finished += 1;
			}
		}

		if (finished == total) {
			check = true;
		}
		DBUtility.closeConnection();
		return check;
	}

	// 自动批改分数
	public static void giveScore(int stuID, int chapterID) throws SQLException {
		int total = find.num2questNum(chapterID);
		int right = 0;
		ResultSet r1 = DBUtility
				.executeQuery("SELECT * from chapter" + chapterID + "_Answers where StudentID=" + stuID);
		r1.next();
		for (int i = 1; i <= total; i++) {
			if (find.num2questIdentity(chapterID, i).equals("choice")) {
				if (find.num2answer(chapterID, i).equals(r1.getString("Answer" + i))) {
					boolean flag1 = DBUtility.executeUpdate("UPDATE chapter" + chapterID + "_Scores SET Answer" + i
							+ "='" + 1 + "' WHERE StudentID=" + stuID);
					right += 1;
				} else {
					boolean flag2 = DBUtility.executeUpdate("UPDATE chapter" + chapterID + "_Scores SET Answer" + i
							+ "='" + 0 + "' WHERE StudentID=" + stuID);
				}
			}
		}
		boolean flag3 = DBUtility
				.executeUpdate("UPDATE chapter" + chapterID + "_Scores SET Done = 'undone' WHERE StudentID=" + stuID);
		boolean flag4 = DBUtility.executeUpdate(
				"UPDATE chapter" + chapterID + "_Scores SET Score = " + right + " WHERE StudentID=" + stuID);
		boolean flag5 = DBUtility.executeUpdate(
				"UPDATE students SET Chapter" + chapterID + " ='submitted'" + "WHERE StudentID=" + stuID);
		DBUtility.closeConnection();
	}

}
