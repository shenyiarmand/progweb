package com.user.survey.service;

import com.user.survey.model.User;

public interface PrincipalService
{
	public User getUserInfo(String username);
	
	public Integer getUserId(String username);
}
