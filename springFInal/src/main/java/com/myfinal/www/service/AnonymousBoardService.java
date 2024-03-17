package com.myfinal.www.service;

import java.util.List;

import com.myfinal.www.domain.anBoardVO;
import com.myfinal.www.domain.nmPagingVO;

public interface AnonymousBoardService {

	int insert(anBoardVO anbvo);

	List<anBoardVO> getList(nmPagingVO pgvo);

	int getTotalCount(nmPagingVO pgvo);

	anBoardVO getDetail(long anbno);

	int modify(anBoardVO anbvo);

	int delete(long anbno);

}
