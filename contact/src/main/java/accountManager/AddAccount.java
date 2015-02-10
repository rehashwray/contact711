package accountManager;

import java.util.ArrayList;

import logger.Log;
import tableModel.User;
import db.DbException;
import db.DbUpdate;

public class AddAccount {
	protected DbUpdate dbUpdate = new DbUpdate();
	
	public AddAccount(){}
	
	public void addAccount(User user){
	
		Integer userId = addUser(user);
		addRole(userId);
	}
	
	public Integer addUser(User user){		
		
		String query = "insert into users "
				+ "(username, user_password) values(?, ?)";
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		
		parameters.add(user.getUsername());
		parameters.add(user.getUserPassword());	
		
		Integer result = null;
		try {
			result = dbUpdate.updateDb(query, parameters, "user_id");
		} catch (DbException e) {
			Log.logError(e);
		}		
		return result;
	}
	
	public Integer addRole(Integer userId){
		
		String query = "insert into user_roles "
				+ "(role, user_id) values('ROLE_USER', ?)";
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		
		parameters.add(userId);

		Integer result = null;
		try {
			result = dbUpdate.updateDb(query, parameters, "user_role_id");
		} catch (DbException e) {
			Log.logError(e);
		}		
		return result;
	}
}
