package com.myfinal.www.service;

import java.util.List;

import com.myfinal.www.domain.cmBoardDTO;
import com.myfinal.www.domain.cmBoardVO;
import com.myfinal.www.domain.nmPagingVO;

public interface CustomerManagerBoardServcie {

	int insert(cmBoardDTO bdto);

	List<cmBoardVO> getList(nmPagingVO pgvo);

	int getTotalCount(nmPagingVO pgvo);

	cmBoardDTO getDetail(long cmbno);

	int modify(cmBoardDTO bdto); 

	int delete(long cmbno);

	int removeFile(String cmUuid);

}
