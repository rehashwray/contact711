package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DbSystemCredentials {

	public static HashMap<String, String> getCredentials(
			ArrayList<Object> parameters) throws DbException {
		Connection connection = null;
		try {
			connection = DbUtilities.openDatabase();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			DbException dbe = new DbException(e.getMessage());
			dbe.setOpenConnection();
			throw dbe;
		}

		PreparedStatement statement = null;
		HashMap<String, String> credentials = new HashMap<String, String>();
		try {
			String query = "select * from system_credentials where key in (?, ?)";
			statement = DbUtilities.prepareStatement(
					connection, 
					query,
					parameters);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				credentials.put(rs.getString("key"), rs.getString("value"));
			}

			rs.close();
		} catch (SQLException e) {
			DbException dbe = new DbException(e.getMessage());
			dbe.setExecutingStatement();
			throw dbe;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				DbException dbe = new DbException(e.getMessage());
				dbe.setClosingStatement();
				throw dbe;
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				DbException dbe = new DbException(e.getMessage());
				dbe.setClosingConnection();
				throw dbe;
			}
		}
		return credentials;
	}
}
