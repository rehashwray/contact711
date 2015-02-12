package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import error.Log;

public class DbUpdate {

	public Integer updateDb(String query, ArrayList<Object> parameters,
			String idName) throws DbException {

		DbException exception = null;
		
		int counter = 0;
		while (counter++ < 1) {
			try {

				Connection connection = null;
				try {
					connection = DbUtilities.openDatabase();
					connection.setAutoCommit(false);
				} catch (SQLException e) {
					DbException dbe = new DbException(e.getMessage());
					dbe.setOpenConnection();
					throw dbe;
				}

				Integer id = null;
				PreparedStatement statement = DbUtilities.prepareStatement(
						connection, query, parameters);
				try {
					try {
						statement.executeUpdate();

						ResultSet rs = statement.getGeneratedKeys();

						if (rs.next()) {
							id = rs.getInt(idName);
						}

						connection.commit();
					} catch (SQLException e) {
						connection.rollback();

						DbException dbe = new DbException(e.getMessage());
						dbe.setExecutingStatement();
						throw dbe;
					}
				} catch (SQLException e) {

					DbException dbe = new DbException(e.getMessage());
					dbe.setExecutingStatement();
					throw dbe;
				} finally {
					try {
						if (statement != null)
							statement.close();

					} catch (SQLException e) {
						DbException dbe = new DbException(e.getMessage());
						dbe.setClosingStatement();
						throw dbe;
					}
					try {
						if (connection != null)
							connection.close();
					} catch (SQLException e) {
						DbException dbe = new DbException(e.getMessage());
						dbe.setClosingConnection();
						throw dbe;
					}
				}
				return id;
			} catch (DbException e) {
				Log.logError(e);
				exception = e; 
			}
		}
		throw exception;	
	}
}
