package com.code.socialmedia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;

import com.code.socialmedia.user.CustomUser;


@Component
public class UserDaoService {

	private static List<CustomUser> users = new ArrayList<>();

	private static int idCount = 0;

	static {
		users.add(new CustomUser(++idCount, "Adam", LocalDate.now().minusYears(30)));

		users.add(new CustomUser(++idCount, "Eve", LocalDate.now().minusYears(25)));
		users.add(new CustomUser(++idCount, "Jim", LocalDate.now().minusYears(20)));
	}

	public List<CustomUser> findAll() {
		return users;
	}

	public CustomUser findOne(int userId) {
		Predicate<? super CustomUser> predicate = user -> user.getId().equals(userId);
		return users.stream().filter(predicate).findFirst().orElse(null);

	}

	public CustomUser saveNewUser(CustomUser user) {

		user.setId(++idCount);
		users.add(user);
		return user;

	}


	public void deleteUser(int userId) {

		Predicate<? super CustomUser> predicate = user -> user.getId().equals(userId);
		users.removeIf(predicate);

	}

	public CustomUser updateUserById(int userId, CustomUser user) {
		CustomUser foundUser = findOne(userId);
		if (foundUser == null) {
			return null;
		}
		users.removeIf(u -> u.getId().equals(userId));
		user.setId(++idCount);
		users.add(foundUser);
		return user;
	}

}
