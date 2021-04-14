package com.jobsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@ApiOperation(value = "Find all users")
	@GetMapping
	public List<UserVO> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "username"));

		return DozerConverter.parseListObjects(service.findAll(pageable), UserVO.class);
	}

	@ApiOperation(value = "Find a specific user by ID")
	@GetMapping("/{id}")
	public UserVO findById(@PathVariable("id") Long id) {
		return DozerConverter.parseObject(service.findById(id), UserVO.class);
	}

}
