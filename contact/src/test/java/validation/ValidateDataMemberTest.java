package validation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tableModel.User;
import accountManager.AddAccount;
import customerManager.Record;
import db.DbUpdate;

public class ValidateDataMemberTest {

	private AddAccount add = new AddAccount();
	private ValidateDataMember validateDataMember = new ValidateDataMember();

	private DbUpdate dbUpdate = new DbUpdate();

	private ArrayList<Record> rtd = null;
		
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

	@Test
	public void testExistUsername() {
				
		User user = new User(-1, "marvin", "cas");			
		Integer userId = add.addUser(user);

		DataMember dataMember = new DataMember("username", "marvinrr", -1);		
		assertFalse(validateDataMember.existUsername(dataMember, "alazi"));
		
		rtd.add(new Record("users", "user_id", userId));		
	}

}
