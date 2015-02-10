package accountManager;

import java.util.ArrayList;

import db.DbException;
import db.DbUpdate;
import tableModel.User;

public class UpdateAccount {

	protected DbUpdate dbUpdate = new DbUpdate();
	protected Integer user_id = null;
	
	public UpdateAccount(){}
	
	public void updateUser(User user){
		String queryUser = "update users "
				+ "set username = ?, "
				+ "user_password = ? "
				+ "where user_id = ?";
		
		ArrayList<Object> parametersUser = new ArrayList<Object>();
		
		parametersUser.add(user.getUsername());
		parametersUser.add(user.getUserPassword());
		parametersUser.add(user.getUserId());
		
		try {
			dbUpdate.updateDb(queryUser, parametersUser, "user_id");
		} catch (DbException e) {
		
		}
	}
}
