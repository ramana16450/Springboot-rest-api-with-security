package com.restapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restapi.entity.UserInfo;
import com.restapi.repository.UserInfoRepository;

@Service
public class UserInfoUserDetailsServiceImpl implements UserDetailsService{

	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo= userInfoRepository.findByUserName(username);
		//return userInfo.map(UserInfoUserDetails::new ).orElseThrow(()-> new UsernameNotFoundException("user "+username+" not found"));
		if (userInfo.isPresent()) {
		    return new UserInfoUserDetails(userInfo.get());
		}
		else {
			throw new UsernameNotFoundException("user " + username + " not found");

		}
	
	}
	

}
