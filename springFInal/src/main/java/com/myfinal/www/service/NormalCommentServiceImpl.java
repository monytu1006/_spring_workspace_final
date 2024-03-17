package com.myfinal.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myfinal.www.domain.nmCommentVO;
import com.myfinal.www.domain.nmPagingVO;
import com.myfinal.www.handler.nmPagingHandler;
import com.myfinal.www.repository.NormalCommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NormalCommentServiceImpl implements NormalCommentService{
	
	private final NormalCommentDAO nmcdao;

	@Override
	public int post(nmCommentVO nmcvo) {
		log.info(">>>>> post serviceimpl>>>>>");
		return nmcdao.insert(nmcvo);
	}

	@Override
	public nmPagingHandler getList(long nmBno, nmPagingVO pgvo) {
		log.info(">>>>> getList >>>>>");
		int totalCount = nmcdao.selectOneBnoTotalCount(nmBno);
		List<nmCommentVO> list = nmcdao.getList(nmBno, pgvo);
		nmPagingHandler ph = new nmPagingHandler(pgvo, totalCount, list);
		return ph;
	}

	@Override
	public int edit(nmCommentVO nmcvo) {
		log.info(">>>>> edit >>>>>");
		return nmcdao.edit(nmcvo);
	}

	@Override
	public int delete(long nmCno) {
		log.info(">>>>> remove >>>>>");
		return nmcdao.delete(nmCno);
	}
	
}
