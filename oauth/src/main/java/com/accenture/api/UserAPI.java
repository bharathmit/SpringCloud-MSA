package com.accenture.api;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.entity.User;
import com.accenture.service.UserService;

@RestController
@RequestMapping("/users")
public class UserAPI {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getUser(Principal principal) {
		return principal;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createUser(@RequestBody User user) {
		userService.create(user);
	}
}
