package messageNotification.controller.authentication;


import java.util.Date;

import javax.validation.Valid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import messageNotification.configs.services.UserUtils;
import messageNotification.entity.authentication.UserLogin;
import messageNotification.model.authentication.ChangePassword;
import messageNotification.model.authentication.Login;
import messageNotification.model.authentication.UserSignUp;
import messageNotification.services.authentication.UserRegistrationService;

@Controller
public class RegistrationController 
{
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@GetMapping("/")
	public String getHello(Model model){
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		return "home";
	}
	
	@GetMapping(value = "/signup")
    public String getSignUpForm( Model model)
	{
        model.addAttribute("userSignUp",new UserSignUp());
        return "/authentication/signup";
    }
	
	@PostMapping(value = "/signup")
    public String submitSignUpForm(@ModelAttribute("userSignUp") @Valid UserSignUp userSignUp, BindingResult result, Model model)
	{
		if(result.hasErrors())
		{
			model.addAttribute("errors",result.getAllErrors());
			return "/authentication/signup";
		}
		
		try {
			userRegistrationService.createNewUser(userSignUp);
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error",e.getMessage());
			return "/authentication/signup";
		}
		
		return "redirect:/login";
    }
	
	@GetMapping(value = "/login")
	public String getLoginForm(@RequestParam(value = "error",required =false) String error, Model model) {
		model.addAttribute("authenticationError",(error!=null?true:false));
		model.addAttribute("userLogin",new Login());
		return "/authentication/login";		
	}
	
	@GetMapping(value = "/change_password")
	public String getChangePasswordForm(Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		model.addAttribute("changePassword",new ChangePassword());
		return "/authentication/change_password";
	}
	
	@PostMapping(value = "/change_password")
	public String setNewPassword(@RequestParam(value = "unmatch",required=false) String unmatch, @ModelAttribute("changePassword") ChangePassword changePassword,BindingResult result,Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		
		if(result.hasErrors()) {
			model.addAttribute("errors",result.getAllErrors());
			return "/authentication/change_password";
		}
		
		if(!userRegistrationService.isEqualCurrentPassword(UserUtils.getUserLogin().getEmail(), changePassword.getCurrentPassword())) {
			model.addAttribute("wrong_current_password",true);
			return "/authentication/change_password";
		}
		
		UserLogin userLogin = new UserLogin();
		
		userLogin.setEmail(UserUtils.getUserLogin().getEmail());
		userLogin.setPassword(changePassword.getConfirmPassword());
		
		userRegistrationService.changeUserPassword(userLogin);
		return "/home";
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder)
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, false);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}
}
