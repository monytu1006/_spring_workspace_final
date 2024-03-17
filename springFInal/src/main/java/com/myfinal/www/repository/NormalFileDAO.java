package com.myfinal.www.repository;

import java.util.List;

import com.myfinal.www.domain.nmFileVO;

public interface NormalFileDAO {

	int insertFile(nmFileVO nmfvo);

	List<nmFileVO> getFileList(long nmbno);

	int removeFile(String nmUuid);

	List<nmFileVO> selectListAllFile();

}
