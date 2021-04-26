package com.jobsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.vo.UserVO;
import com.jobsearch.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "Users Endpoint", tags = "UsersEndpoint")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;

	@ApiOperation(value = "Find a specific user by ID")
	@GetMapping("/{id}")
	public UserVO findById(@PathVariable("id") Long id) {
		return DozerConverter.parseObject(service.findById(id), UserVO.class);
	}

}
