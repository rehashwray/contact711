package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbExistance {
	
	public boolean exist(String query, ArrayList<Object> parameters) throws DbException{

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
		boolean exist = true;
		try {		
			statement = DbUtilities.prepareStatement(connection, query, parameters);
			
			ResultSet rs = statement.executeQuery();				
			
			if(rs.next()){
				exist = rs.getInt(1) > 0;
			}
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
		return exist;
	}
}
