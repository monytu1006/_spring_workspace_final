package com.myfinal.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfinal.www.domain.nmBoardDTO;
import com.myfinal.www.domain.nmBoardVO;
import com.myfinal.www.domain.nmFileVO;
import com.myfinal.www.domain.nmPagingVO;
import com.myfinal.www.repository.NormalBoardDAO;
import com.myfinal.www.repository.NormalFileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NormalBoardServiceImpl implements NormalBoardService{
	
	private final NormalBoardDAO nmbdao;
	
	private final NormalFileDAO nmfdao;

	@Override
	public int insert(nmBoardDTO bdto) {
		log.info(">>>>> normal insert service impl >>>>>");		
		
		int isOk = nmbdao.insert(bdto.getNmbvo());
		
		if(bdto.getFlist()==null) {
			return isOk;
		} else {
			if(isOk > 0 && bdto.getFlist().size()>0) {
				
				long nmBno = nmbdao.selectOneBno();
				for(nmFileVO nmfvo : bdto.getFlist()) {
					nmfvo.setNmBno(nmBno);
					
					isOk *= nmfdao.insertFile(nmfvo);
					
				}
			}
		}
		return isOk;
	}

	@Override
	public List<nmBoardVO> getList(nmPagingVO pgvo) {
		log.info(">>>>> normal getList service impl >>>>>");
		
		nmbdao.updateCommentQty();
		nmbdao.updateFileQty();
		return nmbdao.getList(pgvo);
	}
	
	@Transactional
	@Override
	public nmBoardDTO getDetail(long nmbno) {
		int isOk = nmbdao.readCount(nmbno);
		log.info("readCount is " + ((isOk >0)? "OK" : "Fail"));
		
		nmBoardVO nmbvo = nmbdao.getDetail(nmbno);
		List<nmFileVO> flist = nmfdao.getFileList(nmbno);
		nmBoardDTO bdto = new nmBoardDTO(nmbvo, flist);
		return bdto;
	}

	@Override
	public int modify(nmBoardDTO bdto) {
		log.info(">>>>> normal modify service impl>>>>>");
		
		int isOk = nmbdao.modify(bdto.getNmbvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		} else {
			if(isOk > 0 && bdto.getFlist().size() >0) {
				long nmBno = bdto.getNmbvo().getNmBno();
				
				for(nmFileVO nmfvo : bdto.getFlist()) {
					nmfvo.setNmBno(nmBno);
					isOk = nmfdao.insertFile(nmfvo);
					
				}
			}
		}
		return isOk;
	}

	@Override
	public int delete(long nmbno) {
		log.info(">>>>> normal delete service impl >>>>>");
		return nmbdao.delete(nmbno);
	}

	@Override
	public int getTotalCount(nmPagingVO pgvo) {
		log.info(">>>>> getTotalCount service impl >>>>>");
		return nmbdao.getTotalCount(pgvo);
	}

	@Override
	public int removeFile(String nmUuid) {
		log.info(">>>>> removeFile service impl >>>>>");
		return nmfdao.removeFile(nmUuid);
	}
	
	


}
