package com.jsoftgroup.api;


import java.security.Principal;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jsoftgroup.entity.User;
import com.jsoftgroup.service.UserService;

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
	
	@RequestMapping(value = "/string", method = RequestMethod.GET)
	public String getString() {
		return "Hello";
	}
}
