package com.user.survey.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.user.survey.dao.UserDao;
import com.user.survey.model.User;
import com.user.survey.util.Password;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService 
{
	@Autowired
	private UserDao userDao;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userDao.getUserByEmailId(username);
					
		if(ObjectUtils.isEmpty(user))
			throw new UsernameNotFoundException("Invalid Username or Password !");
		
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority()
	{
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	

	@Override
	public User saveUser(User user) 
	{
		User modelUser = new User();
		
		if(!checkUserIsAvailable(user.getEmailId()))
		{
			user.setPassword(Password.enCryptPassword(user.getPassword()));
			
			user.setDeleted(Boolean.FALSE);
			
			user.setDateAdded(new Date());
			user.setDateModified(new Date());
			
			modelUser = userDao.saveUser(user);
		}
		return modelUser;
	}
	

	@Override
	public User updateUser(User newUser, User oldUser)
	{
		oldUser.setFirstName(newUser.getFirstName());
		oldUser.setLastName(newUser.getLastName());
		oldUser.setMobile(newUser.getMobile());
		
		oldUser.setDateModified(new Date());
		
		return userDao.saveUser(oldUser);
	}
	

	@Override
	public boolean checkUserIsAvailable(String email)
	{		
		User user = userDao.getUserByEmailId(email);
		
		if(!ObjectUtils.isEmpty(user))
			return true;
		else 
			return false;
	}

	

}
