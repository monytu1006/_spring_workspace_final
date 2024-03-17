package com.myfinal.www.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class AuthMember extends User{

	public AuthMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	private MemberVO mvo;
	
		
	public AuthMember(MemberVO mvo) {
		super(mvo.getEmail(), mvo.getPwd(),mvo.getAuthList()
				.stream()
				.map(authVO -> 
				new SimpleGrantedAuthority(authVO.getAuth()))
				.collect(Collectors.toList()));
		this.mvo = mvo;
	}
	
	
}