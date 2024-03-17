package com.myfinal.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myfinal.www.domain.nmCommentVO;
import com.myfinal.www.domain.nmPagingVO;

public interface NormalCommentDAO {

	int insert(nmCommentVO nmcvo);

	List<nmCommentVO> getList(@Param("nmBno") long nmBno, @Param("pgvo") nmPagingVO pgvo);

	int selectOneBnoTotalCount(long nmBno);

	int edit(nmCommentVO nmcvo);

	int delete(long nmCno);



}
