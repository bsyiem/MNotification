package messageNotification.configs.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import messageNotification.dao.authentication.UserLoginDAO;
import messageNotification.entity.authentication.Role;
import messageNotification.entity.authentication.UserLogin;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserLoginDAO userLoginDAO;
	
	@Override
	public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserLogin userLogin = (UserLogin)userLoginDAO.getById(email);	
		

		CustomUserDetails customUserDetails = new CustomUserDetails();
		if(userLogin!=null) {
			Set<GrantedAuthority> authorities = buildUserAuthority(userLogin.getRoles());
			
			customUserDetails.setUser(buildUserForAuthentication(userLogin, authorities));
			customUserDetails.setAuthorities(authorities);
			customUserDetails.setUserId(userLogin.getUser().getId());	
		}else {
			throw new UsernameNotFoundException("User not found");
		}
				
		
		return customUserDetails;
		
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(UserLogin userLogin,Set<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User (userLogin.getEmail(), userLogin.getPassword(),
			userLogin.isEnabled(), true, true, true, authorities);
	 }

	private Set<GrantedAuthority> buildUserAuthority(Set<Role> roles) {

		 Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		 // Build user's authorities
		 for (Role role : roles) {
			 setAuths.add(new SimpleGrantedAuthority(role.getRole()));
		 }

		 Set<GrantedAuthority> Result = new HashSet<GrantedAuthority>(
		 setAuths);

		 return Result;
	}
}
