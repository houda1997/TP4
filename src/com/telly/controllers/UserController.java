package com.telly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showHome() {
		
		return "home";
	}	
	
	
	@RequestMapping(value = "/reservebook", method = RequestMethod.POST)
	public String createReserveBook(@Validated(FormValidationGroup.class) Reserve reserve, BindingResult result, Principal principal) {
		
		if (result.hasErrors()) {
			return "reservebus";
		}
		
		String username = principal.getName();
		reserve.getUser().setUsername(username);
		
		reserveService.reserve(reserve);
	
		
		return "home";

	}
	
	@RequestMapping(value = "/getreservations", method = RequestMethod.GET)
	public String getReserveBook(@Validated(FormValidationGroup.class) Reserve reserve, Model model, Principal principal) {
		
		
		String username = principal.getName();
		reserve.getUser().setUsername(username);
		
		List<Reserve> reserves = reserveService.getReserves(username);
		model.addAttribute("reserves", reserves);
		System.out.println(reserves);
	
		
		return "home";

	}
}
