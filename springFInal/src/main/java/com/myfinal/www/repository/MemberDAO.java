package com.myfinal.www.repository;

import java.util.List;

import com.myfinal.www.security.AuthVO;
import com.myfinal.www.security.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);
	
	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	int modifyPwdEmpty(MemberVO mvo);

	int modify(MemberVO mvo);

	void deleteAuth(String email);

	int detele(String email);

	List<MemberVO> selectList();

	List<AuthVO> selectAuthList(String email);

	MemberVO selectId(String email);

	String getPwd(String email);

	int registerBlackList(MemberVO mvo);

	int cancelBlackList(MemberVO mvo);

}