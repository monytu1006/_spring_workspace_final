package com.myfinal.www.service;

import com.myfinal.www.domain.cmCommentVO;
import com.myfinal.www.domain.cmPagingVO;
import com.myfinal.www.handler.cmPagingHandler;

public interface CustomerManageCommentService {

	int post(cmCommentVO cmcvo);

	cmPagingHandler getList(long cmBno, cmPagingVO pgvo);

	int edit(cmCommentVO cmcvo);

	int delete(long cmCno);

}
