package messageNotification.configs.services;

import org.springframework.security.core.context.SecurityContextHolder;

import messageNotification.entity.authentication.UserLogin;

public class UserUtils {

	private static CustomUserDetails activeUser;
	private static UserLogin userLogin;
	
	public static UserLogin getUserLogin() {
		activeUser = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userLogin = new UserLogin();
		userLogin.setEmail(activeUser.getUsername());
		return userLogin;
	}
	
	public static String getUserName() {
		return (String)SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
