package com.myfinal.www.repository;

import java.util.List;

import com.myfinal.www.domain.cmFileVO;

public interface CustomerManagerFileDAO {

	int insertFile(cmFileVO cmfvo);

	List<cmFileVO> getFileList(long cmbno);

	int removeFile(String cmUuid);
	
}
