package com.user.survey.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password
{
	public static String enCryptPassword(String password)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	            
		return encoder.encode(password);
	}
}
