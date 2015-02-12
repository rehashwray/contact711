package viewInitializer;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import tableModel.User;
import tableModel.UserProfile;

import com.fasterxml.jackson.databind.ObjectMapper;

import db.DbAccount;
import error.ErrorHandler;
import error.Log;

public class UserInitializer extends ErrorHandler {

	public UserInitializer() {}

	public static String initialize() {

		String username = null;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		username = userDetail.getUsername();

		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> cas = (Collection<GrantedAuthority>) userDetail.getAuthorities();
		
		String userProfileJson = null;
		try {
			User user = new DbAccount().getUser(username);
			UserProfile userProfile = new UserProfile(user, cas.toArray()[0].toString());

			userProfileJson = new ObjectMapper().writeValueAsString(userProfile);			

		} catch (Exception e) {
			Log.logError(e);
		}
		return userProfileJson;
	}
}
