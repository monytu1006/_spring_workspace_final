package com.myfinal.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myfinal.www.domain.cmCommentVO;
import com.myfinal.www.domain.cmPagingVO;
import com.myfinal.www.domain.nmPagingVO;
import com.myfinal.www.handler.cmPagingHandler;
import com.myfinal.www.handler.nmPagingHandler;
import com.myfinal.www.repository.CustomerManageCommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerManageCommentServiceimpl implements CustomerManageCommentService{

	private final CustomerManageCommentDAO cmcdao;

	@Override
	public int post(cmCommentVO cmcvo) {
		log.info(">>>>> post serviceimpl>>>>>");
		return cmcdao.insert(cmcvo);
	}

	@Override
	public cmPagingHandler getList(long cmBno, cmPagingVO pgvo) {
		log.info(">>>>> getList >>>>>");
		int totalCount = cmcdao.selectOneBnoTotalCount(cmBno);
		List<cmCommentVO> list = cmcdao.getList(cmBno, pgvo);
		cmPagingHandler ph = new cmPagingHandler(pgvo, totalCount, list);
		return ph;
	}
	
	@Override
	public int edit(cmCommentVO cmcvo) {
		log.info(">>>>> edit >>>>>");
		return cmcdao.edit(cmcvo);
	}

	@Override
	public int delete(long cmCno) {
		log.info(">>>>> delete >>>>>");
		return cmcdao.delete(cmCno);
	}



}
