package messageNotification.services.authentication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import messageNotification.dao.authentication.UserDAO;
import messageNotification.dao.authentication.UserLoginDAO;
import messageNotification.entity.authentication.Role;
import messageNotification.entity.authentication.User;
import messageNotification.entity.authentication.UserLogin;
import messageNotification.model.authentication.UserSignUp;

@Service
public class UserRegistrationService 
{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserLoginDAO userLoginDAO;

	public UserRegistrationService() {
		super();
	}

	public UserRegistrationService(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}
	
	public UserDAO getUserDao() {
		return userDAO;
	}

	public void setUserDao(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void createNewUser(UserSignUp userSignUp) throws Exception {
		
		//check if user already exists
		if(userDAO.getByEmail(userSignUp.getEmail())!=null){
			throw new Exception("Username already Exists");
		}
		
//		default role is assigned to user on signup
		
		Role role = new Role(Role.RoleType.PENDING);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		UserLogin userLogin = new UserLogin();
		userLogin.setEmail(userSignUp.getEmail());
		userLogin.setPassword(passwordEncoder.encode(userSignUp.getPassword()));
		userLogin.setEnabled(true);
		userLogin.setRoles(roles);
		
		
		User user = new User();
		user.setDob(userSignUp.getDob());
		user.setFirstName(userSignUp.getFirstName());
		user.setLastName(userSignUp.getLastName());
		user.setUserLogin(userLogin);
				
		userDAO.create(user);
	}
	
	public void deleteUserByEmail(String email) {
		userDAO.deleteByEmail(email);
	}
	
	public User findUserByEmail(String email) {
		return userDAO.getByEmail(email);
	}
	
	public void changeUserPassword(UserLogin userLogin) {
		userLogin.setPassword(passwordEncoder.encode(userLogin.getPassword()));
		userLoginDAO.updatePassword(userLogin);
	}
	
	public UserLogin findUserLoginByEmail(String email) {
		return (UserLogin)userLoginDAO.getById(email);
	}
	
	public List<UserLogin> findAllUserLogins(){
		return userLoginDAO.getAll();
	}
	
	public List<UserLogin> getUserRoles(){
		return userLoginDAO.getAll();
	}
	
	public boolean isEqualCurrentPassword(String email, String currentPassword) {
		return passwordEncoder.matches(currentPassword, this.findUserLoginByEmail(email).getPassword());
	}
}
