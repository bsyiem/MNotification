package messageNotification.model.authentication;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import messageNotification.validation.annotations.ValidEmail;

public class Login 
{
	@NotNull
	@NotEmpty
	@ValidEmail
	private String email;
	
	@NotNull
	@NotEmpty
	private String password;
	
	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public Login() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
