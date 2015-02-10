package accountManager;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import tableModel.User;
import customerManager.Record;
import db.DbException;
import db.DbExistance;
import db.DbUpdate;

public class DeleteAccountTest {

	private AddAccount userAdd = new AddAccount();
	private DeleteAccount userDelete = new DeleteAccount();

	private DbUpdate dbUpdate = new DbUpdate();
	private DbExistance existance = new DbExistance();

	private ArrayList<Record> rtd = null;
	
	private Integer userId = -1;			
	
	@Before
	public void setUp() throws Exception {
				
		rtd = new ArrayList<Record>();
	}

	@After
	public void tearDown() throws Exception {
		for (Record record : rtd) {

			String query = "delete from " + record.getTable() + " where "
					+ record.getIdName() + " = " + record.getIdValue();

			dbUpdate.updateDb(query, null, record.getIdName());			
			//assertFalse(existance.exist(query, parameters));
		}
	}

	@Ignore
	@Test
	public void testDeleteUser() throws DbException {
		
		String query = "select count(*) from users "
				+ "where username = ? and user_password = ? "
				+ "and user_id = ? ";
				
		User user = new User(userId, "marvin", "cas");			
		Integer userId = userAdd.addUser(user);
		
		user = new User(userId, "marvin222", "cas222");
		userDelete.deleteUser(user.getUsername());
		
		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(user.getUsername());
		parameters.add(user.getUserPassword());
		parameters.add(userId);
		
		assertFalse(existance.exist(query, parameters));
		
		rtd.add(new Record("users", "user_id", userId));
	}

	@Test
	public void testDeleteRole() throws DbException {
		
		String query = "select count(*) from user_roles "
				+ "where username = ? and role = ? "
				+ "and user_role_id = ? ";
				
		User user = new User(userId, "marvin", "cas");			
		Integer userRoleId = userAdd.addRole(user.getUserId());//not possible to exist same id before, no need to test
		
		user = new User(userId, "marvin222", "cas222");
		userDelete.deleteUser(user.getUsername());
		
		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(user.getUsername());
		parameters.add("ROLE_USER");
		parameters.add(userRoleId);
		
		assertFalse(existance.exist(query, parameters));
		
		rtd.add(new Record("user_roles", "user_role_id", userRoleId));
	}	
}
