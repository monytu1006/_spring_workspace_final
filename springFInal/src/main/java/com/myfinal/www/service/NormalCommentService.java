package com.myfinal.www.service;

import com.myfinal.www.domain.nmCommentVO;
import com.myfinal.www.domain.nmPagingVO;
import com.myfinal.www.handler.nmPagingHandler;

public interface NormalCommentService {

	int post(nmCommentVO nmcvo);

	nmPagingHandler getList(long nmBno, nmPagingVO pgvo);

	int edit(nmCommentVO nmcvo);

	int delete(long nmCno);
	
}
