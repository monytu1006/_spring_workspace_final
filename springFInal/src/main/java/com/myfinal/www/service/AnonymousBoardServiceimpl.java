package com.myfinal.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myfinal.www.domain.anBoardVO;
import com.myfinal.www.domain.nmBoardVO;
import com.myfinal.www.domain.nmPagingVO;
import com.myfinal.www.repository.AnonymousBoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnonymousBoardServiceimpl implements AnonymousBoardService{
	
	private final AnonymousBoardDAO anbdao;
	
	
	@Override
	public int insert(anBoardVO anbvo) {
		log.info(">>>>> anonyous insert service impl >>>>>");	
				
		return anbdao.insert(anbvo);
	}


	@Override
	public List<anBoardVO> getList(nmPagingVO pgvo) {
		log.info(">>>>> Anonym1ous getList service impl >>>>>");

		return anbdao.getList(pgvo);
	}


	@Override
	public int getTotalCount(nmPagingVO pgvo) {
		log.info(">>>>> getTotalCount service impl >>>>>");
		return anbdao.getTotalCount(pgvo);
	}


	@Override
	public anBoardVO getDetail(long anbno) {
		int isOk = anbdao.readCount(anbno);
		log.info("readCount is " + ((isOk >0)? "OK" : "Fail"));
		
		return anbdao.getDetail(anbno);
	}


	@Override
	public int modify(anBoardVO anbvo) {
		log.info(">>>>> Anonym1ous modify service impl >>>>>");
		return anbdao.modify(anbvo);
	}


	@Override
	public int delete(long anbno) {
		log.info(">>>>> Anonym1ous delete service impl >>>>>");
		return anbdao.delete(anbno);
	}
	

}
