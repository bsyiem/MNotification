package messageNotification.model.authentication;

import messageNotification.validation.annotations.PasswordMatch;

@PasswordMatch
public interface IPasswordVerify 
{
	public String getPassword();
	public String getConfirmPassword();

}
