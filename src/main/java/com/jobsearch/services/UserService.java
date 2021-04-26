package com.jobsearch.services;

import com.jobsearch.data.model.User;

public interface UserService {
	public User findById(Long id);
}
