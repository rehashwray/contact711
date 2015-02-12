package viewInitializer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import security.Cryptography;
import tableModel.CustomerProfile;

import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.ErrorHandler;
import db.DbCustomers;
import error.Log;

public class ViewCustomerInitializer extends ErrorHandler {

	public static String initialize(HttpServletRequest request) {

		String json = null;

		Integer id = null;
		try {
			id = Integer.parseInt(Cryptography.decrypt(
					request.getParameter("id")));
		} catch (Exception e1) {
			Log.logError(e1);
			return json;
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
