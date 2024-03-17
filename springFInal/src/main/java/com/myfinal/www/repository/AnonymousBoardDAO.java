package com.myfinal.www.repository;

import java.util.List;

import com.myfinal.www.domain.anBoardVO;
import com.myfinal.www.domain.nmPagingVO;

public interface AnonymousBoardDAO {

	int insert(anBoardVO anbvo);

	List<anBoardVO> getList(nmPagingVO pgvo);

	int getTotalCount(nmPagingVO pgvo);

	int readCount(long anbno);

	anBoardVO getDetail(long anbno);

	int modify(anBoardVO anbvo);

	int delete(long anbno);

}
