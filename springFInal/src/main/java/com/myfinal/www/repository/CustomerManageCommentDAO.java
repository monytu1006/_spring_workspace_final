package com.myfinal.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myfinal.www.domain.cmCommentVO;
import com.myfinal.www.domain.cmPagingVO;
import com.myfinal.www.domain.nmPagingVO;

public interface CustomerManageCommentDAO {

	int insert(cmCommentVO cmcvo);

	int selectOneBnoTotalCount(long cmBno);

	List<cmCommentVO> getList(@Param("cmBno") long cmBno, @Param("pgvo") cmPagingVO pgvo);

	int edit(cmCommentVO cmcvo);

	int delete(long cmCno);

}
