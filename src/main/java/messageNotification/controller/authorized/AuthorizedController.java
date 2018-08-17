package messageNotification.controller.authorized;

import java.util.Set;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import messageNotification.ExcelUtils.DataExtract;
import messageNotification.configs.services.UserUtils;

@Controller
public class AuthorizedController {
	
	@GetMapping(value="/authorized/send_notification")
	public String getUploadPage(Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		return "/message_notification/notification";
	}
	
	@PostMapping(value="/authorized/send_notification")
	@ResponseBody
	public Set<Long> getPhoneNumbers(@RequestParam(name = "fileLocation") MultipartFile file, Model model) {
		model.addAttribute("user",UserUtils.getUserLogin().getEmail());
		try {
			DataExtract dataExtract = new DataExtract(file);
			return dataExtract.getAllMobileNumbers();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
