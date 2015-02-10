package viewInitializer;

import javax.servlet.http.HttpServletRequest;

import logger.ErrorHandler;
import logger.Log;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import security.Cryptography;
import tableModel.CustomerProfile;

import com.fasterxml.jackson.databind.ObjectMapper;

import db.DbCustomers;

public class CustomerProfileInitializer extends ErrorHandler {

	public String initialize(HttpServletRequest request) {

		String json = null;

		Integer id = null;
		try {
			id = Integer.parseInt(Cryptography.decrypt(request
					.getParameter("id")));
		} catch (Exception e1) {
			Log.logError(e1);
		}

		if (id != null) {
			String username = null;
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				username = userDetail.getUsername();
			}

			try {
				CustomerProfile customerProfile = new DbCustomers()
						.getCustomerProfile(username, id);

				json = new ObjectMapper().writeValueAsString(customerProfile);
			} catch (Exception e) {
				Log.logError(e);
			}
		}

		return json;
	}
}
