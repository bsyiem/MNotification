package messageNotification.controller.authorized;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import messageNotification.configs.services.UserUtils;

@Controller
public class AuthorizedController {
	
	@GetMapping(value="/send_notification")
	public String getUploadPage(Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		return "/message_notification/notification";
	}

}
