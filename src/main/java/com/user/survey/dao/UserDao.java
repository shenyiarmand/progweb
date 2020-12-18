package com.user.survey.dao;

import com.user.survey.model.User;

public interface UserDao 
{
	public User getUserByEmailId(String email);

	public User saveUser(User user);
}
