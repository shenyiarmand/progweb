package com.user.survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.user.survey.dao.UserDao;
import com.user.survey.exception.ResourceNotFoundException;
import com.user.survey.model.User;

@Service
public class PrincipalServiceImpl implements PrincipalService 
{
	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserInfo(String username)
	{
		User user = userDao.getUserByEmailId(username);
		
		if(ObjectUtils.isEmpty(user))
			throw new ResourceNotFoundException("User", "Email/Username", username);
		
		return user;
	}

	@Override
	public Integer getUserId(String username)
	{
		User user = userDao.getUserByEmailId(username);
		
		if(ObjectUtils.isEmpty(user))
			throw new ResourceNotFoundException("User", "Email/Username", username);
		
		return user.getId();
	}
	
	
	
}
