package messageNotification.controller.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import messageNotification.configs.services.UserUtils;
import messageNotification.entity.authentication.Role;
import messageNotification.entity.authentication.Role.RoleType;
import messageNotification.entity.authentication.UserLogin;
import messageNotification.services.authentication.UserService;

@Controller
public class UserManagementController {
	@Autowired
	UserService userRegistrationService;
	
	
//	@PreAuthorize("hasAuthority('ADMIN')") //can be used if no prior configs were made to allow only admin to access this mapping in springsecurity config 
	@GetMapping(value = "/admin/getUserLoginDetails")
	@ResponseBody
	public List<UserLogin> getUserRoles(){
		return userRegistrationService.findAllUserLogins();
	}
	
/*
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Following mappings relate to management of user roles
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */
	
	@GetMapping(value = "/admin/manageUserRoles")
	public String manageUserRoles(Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		return "/admin/manage_user_roles";
	}
	
	@PostMapping(value = "/admin/manageUserRoles")
	public String setUserRoles(HttpServletRequest request,Model model){
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		
		Map<String,String[]> parameterMap = request.getParameterMap();
		for(Map.Entry<String, String[]> entry: parameterMap.entrySet()) {
			String email = entry.getKey().split("\\[")[0];
			
			//find userlogin by email
			UserLogin userLogin = userRegistrationService.findUserLoginByEmail(email);
			
			userLogin.setRoles(new HashSet<>());
			
			//Set new roles to user
			for(String value: entry.getValue()) {
				Role role = new Role(RoleType.PENDING);
				switch(value) {
				case "ADMIN":
					role = new Role(RoleType.ADMIN);
					break;
				case "AUTHORIZED":
					role = new Role(RoleType.AUTHORIZED);
					break;
				}
				userLogin.addRole(role);
			}
			userRegistrationService.updateUserLogin(userLogin);	
		}
		return "/admin/manage_user_roles";
	}
	
/*
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Following mappings relate to management of user enabled status
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
*/
	
	@GetMapping(value = "/admin/manageUserEnabledStatus")
	public String manageUserEnabledStatus(Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		return "/admin/enable_disable_users";
	}
	
	@PostMapping(value = "/admin/manageUserEnabledStatus")
	public String setUserEnabledStatus(HttpServletRequest request,Model model){
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		Map<String,String[]> parameterMap = request.getParameterMap();
		
		for(Map.Entry<String, String[]> entry: parameterMap.entrySet()) {
			String email = entry.getKey();
			
			UserLogin userLogin = userRegistrationService.findUserLoginByEmail(email);
			
			if(entry.getValue()[0].equals("on")) {
				userLogin.setEnabled(true);
			}else {
				userLogin.setEnabled(false);
			}
			userRegistrationService.updateUserLogin(userLogin);
		}
		return "/admin/manage_user_roles";
	}
	
/*
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Following mappings relate to deletion of users
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */	
	
	@GetMapping(value = "/admin/removeUsers")
	public String getRemoveUsers(Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		return "/admin/delete_users";
	}
	
	@PostMapping(value = "/admin/removeUsers")
	public String removeUsers(HttpServletRequest request,Model model){
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		Map<String,String[]> parameterMap = request.getParameterMap();
		
		for(Map.Entry<String, String[]> entry: parameterMap.entrySet()) {
			String email = entry.getKey();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+ email);
			userRegistrationService.deleteUserByEmail(email);
		}	
		return "/admin/manage_user_roles";
	}
}
