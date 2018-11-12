package com.telly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/loggedout")
	public String showLogout() {
		return "loggedout";
	}
	

	@RequestMapping("/createaccount")
	public String createAccount(Model model, Principal principal) {
		
		model.addAttribute("user", new User());
		
		return "createaccount";
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createUser(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		
		if(result.hasErrors()) {
			return "createaccount";
		}
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		userService.create(user);
		
		return "home";

	}

}
