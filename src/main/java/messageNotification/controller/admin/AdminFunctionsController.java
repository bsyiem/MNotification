package messageNotification.controller.admin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import messageNotification.configs.services.UserUtils;
import messageNotification.entity.authentication.Role;
import messageNotification.entity.authentication.Role.RoleType;
import messageNotification.entity.authentication.UserLogin;
import messageNotification.services.authentication.UserRegistrationService;

@Controller
public class AdminFunctionsController {
	@Autowired
	UserRegistrationService userRegistrationService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value="/admin/getUserLoginDetails")
	@ResponseBody
	public List<UserLogin> getUserLoginDetails(){
		return userRegistrationService.findAllUserLogins();
	}
	
	@GetMapping(value = "/admin/getUserRoles")
	@ResponseBody
	public List<UserLogin> getUserRoles(){
		return userRegistrationService.getUserRoles();
	}
	
	@GetMapping(value = "/admin/manageUserRoles")
	public String manageUserRoles(Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		return "/admin/manage_user_roles";
	}
	
	@PostMapping(value = "/admin/manageUserRoles")
	public String setUserRoles(HttpServletRequest request,Model model){
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		
		/*
		 * gets the map from parameter name to values
		 * here since the checkboxes used are arrays, the map is from a String to String[]
		 * i.e, param_name -> values[]
		 * single name contains multiple values
		 */
		Map<String,String[]> parameterMap = new HashMap<>(request.getParameterMap());
		
		/*
		* we select the first key in the map (which is the email) and update the roles (values[]) of the userLogin associated with that email
		* we remove the key (email) after the userLogin has been updated
		* NOTE: the request.getParameterMap only receives data where the checkboxes have been "checked" and so when we remove all roles from
		* a user, that user is not in the parameterMap keySet and so the userLogin will not be updated. To address this issue we add value[0] = PENDING
		* to a user when all other checkboxes (ADMIN, AUTHORIZED) are not checked (done in the manage_user_roles.jsp). 
		*/
		while(!parameterMap.isEmpty()){
			
			Map.Entry<String, String[]> entry = parameterMap.entrySet().iterator().next();
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
	
			userRegistrationService.updateRoles(userLogin);			
			parameterMap.remove(entry.getKey());
		}
		return "/admin/manage_user_roles";
	}
}
