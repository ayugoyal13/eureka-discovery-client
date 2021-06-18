package com.epam.eurekadiscoveryclient.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.epam.eurekadiscoveryclient.model.User;

@RestController
@RequestMapping("/api/user-service")
public class UserServiceDiscoveryClient {

	@Autowired
	RestTemplate template;
	
	private static final String WELCOME_URL = "http://USER-SERVICE/userservice/";
	private static final String FIND_ALL_USERS_URL = "http://USER-SERVICE/userservice/users";
	private static final String FIND_USER_BY_ID_URL = "http://USER-SERVICE/userservice/users/{userId}";
	private static final String ADD_NEW_USER_URL = "http://USER-SERVICE/userservice/users";
	private static final String UPDATE_EXISTING_USER_URL = "http://USER-SERVICE/userservice/users";
	private static final String DELETE_USER_BY_ID_URL = "http://USER-SERVICE/userservice/users/{userId}";
	
	@GetMapping(path = "/")
	public String welcomeUserService() {
		return template.getForObject(WELCOME_URL, String.class);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/users")
	public ResponseEntity<List<User>> findAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			users = template.getForObject(FIND_ALL_USERS_URL, List.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(path = "/users/{userId}")
	public ResponseEntity<User> findUserById (@PathVariable long userId) {
		User user = null;
		try {
			user = template.getForObject(FIND_USER_BY_ID_URL, User.class, userId);
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping(path = "/users", consumes = "application/json")
	public ResponseEntity<User> addNewUser(@RequestBody User user) {
		User result = null;
		try {
			User newUser = new User(0, user.getUserName(), user.getUserPhone(), user.getUserAddress());
			result = template.postForObject(ADD_NEW_USER_URL, newUser, User.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(result, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/users", consumes = "application/json")
	public ResponseEntity<User> updateExistingUser(@RequestBody User user) {
		ResponseEntity<User> entity = null;
		try {
			template.put(UPDATE_EXISTING_USER_URL, user);
			entity = findUserById(user.getUserId());
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@DeleteMapping(path = "/users/{userId}")
	public ResponseEntity<User> deleteUserById(@PathVariable long userId) {
		try {
			template.delete(DELETE_USER_BY_ID_URL, userId);
			return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
