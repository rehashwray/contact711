package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tableModel.User;

public class DbAccount {

	public Integer getUserId(String username) throws DbException{
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
		Integer user_id;
		try {		
			statement = connection.prepareStatement(
					"select user_id from users where username = '" + username + "'");
			
			ResultSet rs = statement.executeQuery();				
			
			rs.next();
			user_id = rs.getInt(1);

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
		return user_id;
	}
	
	public User getUser(String username) throws DbException{
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
		User user;
		try {		
			statement = connection.prepareStatement(
					"select * from users where username = '" + username + "'");
			
			ResultSet rs = statement.executeQuery();				
			
			rs.next();
			user = new User(rs.getInt("user_id"), rs.getString("username"),
					rs.getString("user_password"));
			
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
		return user;
	}
	
	public String getUserPassword(String username) throws DbException{
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
		String password = null;
		try {		
			statement = connection.prepareStatement(
					"select user_password from users where username = '" + username + "'");
			
			ResultSet rs = statement.executeQuery();				
			
			if(rs.next()){
				password = rs.getString("user_password");
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
		return password;
	}
}
