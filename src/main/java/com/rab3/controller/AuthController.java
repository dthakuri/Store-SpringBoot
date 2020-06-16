package com.rab3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.service.EmailService;
import com.rab3.service.ProfileService;

@Controller
public class AuthController {
	
	/*@Autowired
	@Qualifier("pkdataSource")
	private DataSource ds;*/
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/foo")
	public String helloWorld() {
		return "hello";   //   /aha.jsp
	}
	
	@GetMapping({"/auth","/login","/","/index"})
	public String showLogin() {
		 return "login";
	}
	
	//action=auth
	//method = POST
	@PostMapping("/auth")
	public String auth(@RequestParam String username,@RequestParam String password,
			Model model,HttpSession  session) {
			ProfileDTO profileDTO=profileService.auth(username, password);
			if(profileDTO!=null) {
				session.setAttribute("profileDTO", profileDTO);
				model.addAttribute("magic", profileDTO);
				return "home";  //     /home.jsp
			}else {
				model.addAttribute("msg", "Sorry!! username and password are not valid!!!!!!!!!!!!!!!");
				return "login";
			}
	}
	
	@GetMapping("/tem")
	public String template(Model model) {
		model.addAttribute("count", profileService.findCounts());
		return "tem1";
	}
	
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/email")
	public String emailsender() {
		
		//emailService.emailSender("hello, testing");
		//List<ProfileDTO> profileDTOs = profileService.findProfiles(); emailService.emailSender(profileDTOs);
		emailService.emailSender();
		return "email";
	}
	
	
	
	
	

}