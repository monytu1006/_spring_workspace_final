package com.myfinal.www.service;

import java.util.List;

import com.myfinal.www.domain.nmBoardDTO;
import com.myfinal.www.domain.nmBoardVO;
import com.myfinal.www.domain.nmPagingVO;

public interface NormalBoardService {

	int insert(nmBoardDTO bdto);

	List<nmBoardVO> getList(nmPagingVO pgvo);

	nmBoardDTO getDetail(long nmBno);

	int modify(nmBoardDTO bdto);

	int delete(long nmbno);

	int getTotalCount(nmPagingVO pgvo);

	int removeFile(String nmUuid);


}
