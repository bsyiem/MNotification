package messageNotification.controller.admin;

import java.util.List;

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
	public String setUserRoles(HttpServletRequest request,Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		
		for(UserLogin uLogin: userRegistrationService.findAllUserLogins()) {
			for(Role.RoleType role: Role.RoleType.values()) {			
				/*
				 * removes role if the respective checkbox is unchecked and adds role if box is checked
				 * duplicate roles are not added as the UserLogin.roles attribute is a Set
				*/
				if(request.getParameter(uLogin.getEmail()+"_"+role.toString()) == null) {
					uLogin.removeRole(new Role(role));
				}
				else {
					uLogin.addRole(new Role(role));
				}
				
				/*
				 * if no role is set then default to pending
				 */
				if(uLogin.getRoles().size()==0) {
					uLogin.addRole(new Role(RoleType.PENDING));
				}
			}
			userRegistrationService.updateRoles(uLogin);
		}
		return "/admin/manage_user_roles";
	}
}
