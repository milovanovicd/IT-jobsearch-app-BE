package com.jobsearch.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jobsearch.data.model.User;

public interface UserService {
	public List<User> findAll(Pageable pageable);

	public User findById(Long id);
}
