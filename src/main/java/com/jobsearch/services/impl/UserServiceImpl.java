package com.jobsearch.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobsearch.data.model.Permission;
import com.jobsearch.data.model.User;
import com.jobsearch.repository.UserRepository;
import com.jobsearch.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	UserRepository repository;

	public UserServiceImpl() {
	}

	public UserServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}

	}

	@Override
	public User findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
	}

	@Override
	public void delete(Long id) {
		User user = this.findById(id);
		
		for(Permission p : user.getPermissions()) {
			p.removeUser(user);
		}
		
		user.setCompany(null);
		user.setCandidate(null);
		
		repository.deleteUser(user.getId());
		
	}

}
