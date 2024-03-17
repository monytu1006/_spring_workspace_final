package com.myfinal.www.repository;

import java.util.List;

import com.myfinal.www.domain.cmBoardVO;
import com.myfinal.www.domain.nmPagingVO;

public interface CustomerManagerBoardDAO {

	int insert(cmBoardVO cmbvo);

	long selectOneBno();

	void updateCommentQty();

	void updateFileQty();

	List<cmBoardVO> getList(nmPagingVO pgvo);

	int getTotalCount(nmPagingVO pgvo);

	int readCount(long cmbno);

	cmBoardVO getDetail(long cmbno);

	int modify(cmBoardVO cmbvo);

	int delete(long cmbno);

	
}
