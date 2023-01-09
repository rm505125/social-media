package com.code.socialmedia.controller;

import java.net.URI;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.code.socialmedia.controller.exception.UserNotFoundException;
import com.code.socialmedia.service.UserDaoService;
import com.code.socialmedia.user.CustomUser;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/social")
public class UserResource {

	private UserDaoService service;

	public UserResource(UserDaoService service) {
		super();
		this.service = service;
	}

	@GetMapping("/users")
	public List<CustomUser> retrieveAllUsers() {
		return service.findAll();
	}

	// EntiryModel
	// WebMvcLinkBuilder

	@GetMapping("/users/{userId}")
	public EntityModel<CustomUser> retrievUserById(@PathVariable int userId) {
		CustomUser user = service.findOne(userId);
		if (user == null) {
			throw new UserNotFoundException("id : " + userId);
		}

		EntityModel<CustomUser> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}

	@PostMapping("/users")
	public ResponseEntity<CustomUser> addNewUser(@Valid @RequestBody CustomUser user) {
		CustomUser savedUser = service.saveNewUser(user);
		// int userId = savedUser.getId();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUserById(@PathVariable int userId) {

		service.deleteUser(userId);

		ResponseEntity.ok().build();
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<CustomUser> updateUser(@PathVariable int userId, @Valid @RequestBody CustomUser user) {
		service.updateUserById(userId, user);
		return ResponseEntity.accepted().build();
	}

}
