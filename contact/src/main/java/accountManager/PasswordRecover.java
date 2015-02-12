package accountManager;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import administrator.SystemCredentials;

import com.google.common.collect.ImmutableMap;

import db.DbAccount;
import db.DbException;
import error.Log;

public class PasswordRecover {

	public static void recoverPassword(String username) {

		String password = null;
		try {
			DbAccount ua = new DbAccount();
			password = ua.getUserPassword(username);
		} catch (DbException e) {
			Log.logError(e);
			return;
		}

		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add("emailAddress");
		parameters.add("emailPassword");

		ImmutableMap<String, String> credentials = null;		
		credentials = SystemCredentials.getEmailCredentials();		

		String subject = "Password";
		String body = "Password: " + password;

		Properties properties = System.getProperties();
		String host = "smtp.gmail.com";

		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", host);
		properties.put("mail.smtp.user", credentials.get("emailAddress"));
		properties.put("mail.smtp.password", credentials.get("emailPassword"));
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);

		try {

			message.setFrom(new InternetAddress(credentials.get("emailAddress")));
			InternetAddress destination = new InternetAddress(username);

			message.addRecipient(Message.RecipientType.TO, destination);
			message.setSubject(subject);
			message.setText(body);

			Transport transport = session.getTransport("smtp");

			transport.connect(
					host, 
					credentials.get("emailAddress"),
					credentials.get("emailPassword"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			Log.logError(e);
		}
	}
}
