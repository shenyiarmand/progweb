package com.user.survey.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.survey.constant.KeyConstant;
import com.user.survey.model.ServiceResponse;
import com.user.survey.model.User;
import com.user.survey.service.PrincipalService;
import com.user.survey.service.UserService;

@RestController
@RequestMapping(value = "/user", produces = "application/hal+json")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrincipalService principalService;
	
//----------------------------------------------------------------------------------------NEW USER REGISTRATION------------------------------
	
	@PostMapping("/signup")
	public ResponseEntity<Object> saveUser(@RequestBody User user)
	{
		User modelUser = userService.saveUser(user);
		
		if(!ObjectUtils.isEmpty(modelUser.getId()))
		{
			ServiceResponse<String> response = new ServiceResponse<String>(KeyConstant.STATUS_SUCCESS, modelUser.getFirstName()+" "+modelUser.getLastName());
			return new ResponseEntity<Object>(response, HttpStatus.CREATED);
		}
		else
		{
			ServiceResponse<String> response = new ServiceResponse<String>(KeyConstant.STATUS_FAILED, modelUser.getFirstName()+" "+modelUser.getLastName());
			return new ResponseEntity<Object>(response, HttpStatus.ALREADY_REPORTED);
		}
		
	}
	
//----------------------------------------------------------------------------------------UPDATE USER INFORMATION-----------------------------

	@PutMapping()
	public ResponseEntity<Object> updateUser(@RequestBody User user, Principal principl)
	{
		User modelUser = userService.updateUser(user, principalService.getUserInfo(principl.getName()));
		
		ServiceResponse<User> response = new ServiceResponse<User>(KeyConstant.STATUS_SUCCESS, modelUser);
		return new ResponseEntity<Object>(response, HttpStatus.OK);		
	}
	
	@GetMapping()
	public ResponseEntity<Object> getUser(Principal principl)
	{
		User user = principalService.getUserInfo(principl.getName());
		ServiceResponse<User> response = new ServiceResponse<User>(KeyConstant.STATUS_SUCCESS, user);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

//------------------------------------------------------------------------------------------END-----------------------------------------------
	
}
