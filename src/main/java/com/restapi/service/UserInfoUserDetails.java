package com.restapi.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.restapi.entity.UserInfo;

public class UserInfoUserDetails implements UserDetails {
	
	
	private String userName;
	private String password;
	private List<SimpleGrantedAuthority> grantedAuthorities;
	
	
	public UserInfoUserDetails(UserInfo userInfo) {
		
		userName=userInfo.getUserName();
		password=userInfo.getPassword();
		grantedAuthorities=Arrays.stream(userInfo.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

}
