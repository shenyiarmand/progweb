package com.user.survey.service;

import com.user.survey.model.User;

public interface UserService
{
	public User saveUser(User user);
	
	public boolean checkUserIsAvailable(String email);

	public User updateUser(User newUser, User oldUser);
}
