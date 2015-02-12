package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import admin.SystemCredentials;
import error.Log;

public class DbUtilities {

	public static Connection openDatabase() throws SQLException {

		return DriverManager.getConnection(
				SystemCredentials.getDbUrl(), 
				SystemCredentials.getDbUser(), 
				SystemCredentials.getDbPassword());
	}
	
	public static PreparedStatement prepareStatement(
			Connection connection, 
			String query,
			ArrayList<Object> parameters){
		
		PreparedStatement statement = null;
		try {
			if(!query.contains("select")){
				statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			}else{
				statement = connection.prepareStatement(query);
			}
			
			if (parameters != null) {
				int parameterIndex = 1;
				for (Object parameter : parameters) {

					String paramClassType = parameter.getClass().toString();

					if (paramClassType.contains("Integer")) {
						statement.setInt(parameterIndex, (Integer) parameter);
					} else {
						statement.setString(parameterIndex, (String) parameter);
					}
					parameterIndex++;
				}
			}
		} catch (SQLException e) {
			Log.logError(e);
		}		
		
		return statement;
	}
	
	private static ArrayList<Integer> indexList(
			String sentence, String target, ArrayList<Integer> indices){
		
		int index = sentence.indexOf(target);
		while (index >= 0) {
		    indices.add(index);
		    index = sentence.indexOf(target, index + 1);
		}
		
		return indices;
	}
	
	public static void print(String query){
		
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		indices.add(0);
		indices = indexList(query, " inner join ", indices);
		indices = indexList(query, " on ", indices);
		indices = indexList(query, " where ", indices);
		indices = indexList(query, " and ", indices);
		indices = indexList(query, " or ", indices);
		indices.add(query.length());
		
		Collections.sort(indices, new Comparator<Integer>() {
	        public int compare(Integer element1, Integer element2)
	        {
	            return  element1.compareTo(element2);
	        }
	    });
				
		for(int index = 0; index < indices.size() - 1; index++)
			System.out.println(
					query.substring(
							indices.get(index), indices.get(index + 1)));		
	}
}
