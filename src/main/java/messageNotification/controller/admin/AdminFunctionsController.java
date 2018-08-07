package messageNotification.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
