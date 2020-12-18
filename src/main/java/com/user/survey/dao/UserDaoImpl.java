package com.user.survey.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.survey.model.User;
import com.user.survey.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao 
{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserByEmailId(String email) 
	{
		return userRepository.findByEmailIdAndIsDeleted(email, Boolean.FALSE);
	}

	@Override
	public User saveUser(User user) 
	{
		return userRepository.save(user);
	}

}
