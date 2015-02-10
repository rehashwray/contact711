package accountManager;

import static org.junit.Assert.assertTrue;

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

public class UpdateAccountTest {

	private AddAccount userAdd = new AddAccount();
	private UpdateAccount userUpdate = new UpdateAccount();

	private DbUpdate dbUpdate = new DbUpdate();;
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
	public void testUpdateUser() throws DbException {
		
		String query = "select count(*) from users "
				+ "where username = ? and user_password = ? "
				+ "and user_id = ? ";
				
		User user = new User(userId, "marvin", "cas");			
		Integer userId = userAdd.addUser(user);
		
		user = new User(userId, "marvin222", "cas222");
		userUpdate.updateUser(user);
		
		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(user.getUsername());
		parameters.add(user.getUserPassword());
		parameters.add(userId);
		
		assertTrue(existance.exist(query, parameters));
		
		rtd.add(new Record("users", "user_id", userId));
	}	
}
