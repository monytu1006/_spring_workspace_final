package com.myfinal.www.service;

import java.util.List;

import com.myfinal.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	MemberVO detail(String email);

	int modifyPwdEmpty(MemberVO mvo);

	int modify(MemberVO mvo);

	List<MemberVO> getList();

	int remove(String email);

	MemberVO checkEmail(String email);

	String getPwd(String email);

	int registerBlackList(MemberVO mvo);

	int cancelBlackList(MemberVO mvo);


}
