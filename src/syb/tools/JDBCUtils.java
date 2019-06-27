package syb.tools;





import java.sql.*;

public class JDBCUtils {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/shoppingweb?useUnicode=true&characterEncoding=UTF-8";
		String username = "root";
		String password = "shanyunbiao";
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void release(Statement stmt, Connection conn) {
		if(null != stmt) {
			try {
				stmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != conn) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		if(null != rs) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		release(stmt, conn);
	}
}
