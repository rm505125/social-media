package com.code.socialmedia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;

import com.code.socialmedia.user.CustomUser;

@Component
public class UserDaoService {
	
	private static List<CustomUser> users = new ArrayList<>();
	
	static {
		users.add(new CustomUser(1,"Adam",LocalDate.now().minusYears(30)));
		
		users.add(new CustomUser(1,"Eve",LocalDate.now().minusYears(25)));
		users.add(new CustomUser(1,"Jim",LocalDate.now().minusYears(20)));
	}

}
