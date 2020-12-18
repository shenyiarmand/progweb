package com.user.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.user.survey.model.User;

@Controller
public class ViewController 
{
	
//-----------------------------------------------------------------------------------GET HOME PAGE------------------------------

	@GetMapping()
	public String getHomePage()
	{	
		return "home";
	}
	
	@GetMapping("/registration")
	public String getRegistrationPage(Model model)
	{	
		model.addAttribute("user", new User());
		
		return "registration";
	}
	
	@GetMapping("/login")
	public String getLoginPage()
	{	
		return "login";
	}
	
	@GetMapping("/allSurvey")
	public String getallSurveyPage()
	{	
		return "allSurvey";
	}
	
	@GetMapping("/profile")
	public String getProfile()
	{	
		return "profile";
	}
	
	
}
