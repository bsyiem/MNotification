package messageNotification.configs.services;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 3780754687116668599L;
	private org.springframework.security.core.userdetails.User user;
	private Integer userId;
	Set<GrantedAuthority> authorities = null;
	
	public org.springframework.security.core.userdetails.User getUser() {
		return user;
	}
 
	public void setUser(org.springframework.security.core.userdetails.User user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		//all users are currently set to have unlimited retention time in the system
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Users are curretly all non locked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Passwords are retained until user/admin changes it
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.user.isEnabled();
	}

}
