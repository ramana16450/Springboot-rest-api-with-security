package com.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.entity.UserInfo;
import com.restapi.service.UserInfoService;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {
	
	
	@Autowired
	private UserInfoService userInfoService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody UserInfo userInfo) {
		
		 userInfoService.createUserInfo(userInfo);
		 
		 return new ResponseEntity<>("User has been created", HttpStatus.ACCEPTED);
		
	}

}
