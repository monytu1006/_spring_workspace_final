package com.myfinal.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfinal.www.repository.MemberDAO;
import com.myfinal.www.security.AuthVO;
import com.myfinal.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberDAO mdao;	
	
	@Transactional
	@Override
	public int register(MemberVO mvo) {
		log.info(">>>>> register seviceImpl mvo >>>>"+mvo);
		int isOk = mdao.insert(mvo);
		log.info(">>>>>>>>>>>>>>>>>>>>> insert DAO is >>>>>"+(isOk>0?"OK":"Fail"));
		return mdao.insertAuthInit(mvo.getEmail());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		log.info(">>>>> updateLastLogin seviceImpl >>>>> ");
		return mdao.updateLastLogin(authEmail)>0? true:false;
	}

	@Override
	public MemberVO detail(String email) {
		log.info(">>>>> detail seviceImpl >>>>> ");
		MemberVO mvo = mdao.selectEmail(email);
		mvo.setAuthList(mdao.selectAuths(email));
		
		return mvo;
	}

	@Override
	public int modifyPwdEmpty(MemberVO mvo) {
		log.info(">>>>> modifyPwdEmpty seviceImpl >>>>> ");
		return mdao.modifyPwdEmpty(mvo);
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info(">>>>> modify seviceImpl >>>>> ");
		return mdao.modify(mvo);
	}
	
	@Override
	public List<MemberVO> getList() {
		log.info(">>>>> getList >>>>>");

		List<MemberVO> list = mdao.selectList();
		
		for(MemberVO mvo : list) {
			String email = mvo.getEmail();
			List<AuthVO> authList = mdao.selectAuthList(email);
			mvo.setAuthList(authList);
		}
		return list;
	}
	
	

	@Override
	public int remove(String email) {
		log.info(">>>>> remove seviceImpl >>>>> ");
		
		mdao.deleteAuth(email);
		
		return mdao.detele(email);
	}

	@Transactional
	@Override
	public MemberVO checkEmail(String email) {
		log.info(">>>>> checkEmail seviceImpl >>>>> ");
		return mdao.selectId(email);
	}

	@Override
	public String getPwd(String email) {
		log.info(">>>>> getPw seviceImpl >>>>> ");
		return mdao.getPwd(email);
	}

	@Override
	public int registerBlackList(MemberVO mvo) {
		log.info(">>>>> registerBlackList seviceImpl >>>>> ");
		return mdao.registerBlackList(mvo);
	}

	@Override
	public int cancelBlackList(MemberVO mvo) {
		log.info(">>>>> cancelBlackList seviceImpl >>>>> ");
		return mdao.cancelBlackList(mvo);
	}






}
