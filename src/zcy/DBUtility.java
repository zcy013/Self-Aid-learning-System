package zcy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtility {
	/* 定义url,user,pwd等 */
	private final static String url = "jdbc:mysql://localhost:3306/learning_system?useSSL=true";
	private final static String user = "root";
	private final static String pwd = "******";

	private static Connection connection;


	/* 执行增删改操作 */
	public static boolean executeUpdate(String sql) {
		try {
			connection = DriverManager.getConnection(url, user, pwd);
			Statement cmd = connection.createStatement();
			cmd.executeUpdate(sql);
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/* 执行查询操作 */
	public static ResultSet executeQuery(String sql) {
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(url, user, pwd);
			Statement cmd = connection.createStatement();
			resultSet = cmd.executeQuery(sql);
			//connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/* 执行关闭数据库连接操作 */
	public static void closeConnection() {
		try {
			if (!connection.isClosed())
				connection.close();
		} catch (Exception e) {
		}
	}
}
