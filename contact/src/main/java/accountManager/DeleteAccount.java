package accountManager;

import java.util.ArrayList;

import logger.Log;
import customerProfileManager.Delete;
import db.DbCustomers;
import db.DbException;
import db.DbUpdate;

public class DeleteAccount {

	protected DbUpdate dbUpdate = new DbUpdate();
	protected Integer user_id = null;
	
	public DeleteAccount(){}
	
	public void deleteAccount(String username){
		try {
			new Delete().deleteCustomers(
					new DbCustomers().getCustomerIds(username));
			
			Integer userId = deleteUser(username);
			deleteRole(userId);
		} catch (DbException e) {
			Log.logError(e);
		}
	}
	
	public Integer deleteUser(String username){
		
		String queryUser = "delete from users where username = ?";
		
		ArrayList<Object> parametersUser = new ArrayList<Object>();
		
		parametersUser.add(username);
		
		Integer userId = null;
		try {
			userId = dbUpdate.updateDb(queryUser, parametersUser, "user_id");
		} catch (DbException e) {
		
		}
		return userId;
	}
	
	public void deleteRole(Integer userId){
		
		String queryUser = "delete from users where user_id = ?";
		
		ArrayList<Object> parametersUser = new ArrayList<Object>();
		
		parametersUser.add(userId);
		
		try {
			dbUpdate.updateDb(queryUser, parametersUser, "user_id");
		} catch (DbException e) {
		
		}
	}
}
