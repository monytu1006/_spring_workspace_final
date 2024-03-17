package com.myfinal.www.repository;

import java.util.List;

import com.myfinal.www.domain.nmBoardVO;
import com.myfinal.www.domain.nmPagingVO;

public interface NormalBoardDAO {

	int insert(nmBoardVO nmbvo);

	List<nmBoardVO> getList(nmPagingVO pgvo);

	int readCount(long nmbno);

	nmBoardVO getDetail(long nmbno);

	int modify(nmBoardVO nmbvo);

	int delete(long nmbno);

	int getTotalCount(nmPagingVO pgvo);

	long selectOneBno();

	void updateCommentQty();

	void updateFileQty();


}
