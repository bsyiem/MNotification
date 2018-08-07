package messageNotification.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import messageNotification.entity.authentication.User;
import messageNotification.services.authentication.UserRegistrationService;

@Controller
public class testController {
	
	@Autowired
	private UserRegistrationService userRegistrationService;
    
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "email", defaultValue = "bsyiem92@gmail.com", required = false) String email, Model model) {
    	userRegistrationService.deleteUserByEmail(email);
    	return "home";
    }
    
    @RequestMapping("/findUserByEmail")
    public String findUserByEmail(@RequestParam(value = "email", defaultValue = "bsyiem92@gmail.com", required = false) String email, Model model) {
    	User user = userRegistrationService.findUserByEmail(email);
    	model.addAttribute("user",user.getFirstName());
    	return "home";
    }
}
