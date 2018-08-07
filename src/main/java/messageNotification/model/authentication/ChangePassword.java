package messageNotification.model.authentication;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ChangePassword implements IPasswordVerify{
	
	@NotNull
	@NotEmpty
	private String currentPassword;
	
	
	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String confirmPassword;

	public ChangePassword(@NotNull @NotEmpty String currentPassword, @NotNull @NotEmpty String password, @NotNull @NotEmpty String confirmPassword) {
		super();
		this.currentPassword = currentPassword;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public ChangePassword() {
		super();
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
