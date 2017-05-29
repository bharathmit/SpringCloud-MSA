package com.accenture.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.entity.User;
import com.accenture.repo.UserRepository;

@Service
public class OAuthUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return user;
	}
}
