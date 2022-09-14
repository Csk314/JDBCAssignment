package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {
	public static void main(String[] args) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		Statement st = null;
		ResultSet res = null;
		try {

			if (conn != null) {
				System.out.println("Connected");

				st = conn.createStatement();
				String query = "select * from books";
				res = st.executeQuery(query);

				while (res.next()) {
					System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " "
							+ res.getFloat(4) + " " + res.getInt(5));
				}

			}

			ConnectionManager.closeConnection();

		} catch (Exception e) {
			System.out.println(e);
		}

		finally {
			// close JDBC objects
			try {
				if (res != null)
					res.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			if (conn != null) {
				ConnectionManager.closeConnection();
			}
		}
	}
}
