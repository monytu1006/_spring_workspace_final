package com.myfinal.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfinal.www.domain.cmBoardDTO;
import com.myfinal.www.domain.cmBoardVO;
import com.myfinal.www.domain.cmFileVO;
import com.myfinal.www.domain.nmPagingVO;
import com.myfinal.www.repository.CustomerManagerBoardDAO;
import com.myfinal.www.repository.CustomerManagerFileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerManagerBoardServcieimple implements CustomerManagerBoardServcie{

	private final CustomerManagerBoardDAO cmbdao;
	
	private final CustomerManagerFileDAO cmfdao;

	@Override
	public int insert(cmBoardDTO bdto) {
		log.info(">>>>> customer insert service impl >>>>>");		
		
		int isOk = cmbdao.insert(bdto.getCmbvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		if(isOk>0 && bdto.getFlist().size()>0) {
			long cmbno = cmbdao.selectOneBno();
			for(cmFileVO cmfvo : bdto.getFlist()) {
				cmfvo.setCmBno(cmbno);;
				isOk *= cmfdao.insertFile(cmfvo);
			}
		}
		
		return isOk;
	}

	@Override
	public List<cmBoardVO> getList(nmPagingVO pgvo) {
		log.info(">>>>> customer getList service impl >>>>>");
		
		cmbdao.updateCommentQty();
		cmbdao.updateFileQty();
		
		return cmbdao.getList(pgvo);
	}

	@Override
	public int getTotalCount(nmPagingVO pgvo) {
		log.info(">>>>> getTotalCount service impl >>>>>");
		return cmbdao.getTotalCount(pgvo);
	}

	@Transactional
	@Override
	public cmBoardDTO getDetail(long cmbno) {
		int isOk = cmbdao.readCount(cmbno);
		log.info("readCount is " + ((isOk >0)? "OK" : "Fail"));
		
		cmBoardVO cmbvo = cmbdao.getDetail(cmbno);
		List<cmFileVO> flist = cmfdao.getFileList(cmbno);
		cmBoardDTO bdto = new cmBoardDTO(cmbvo, flist);
		return bdto;
	}

	@Override
	public int modify(cmBoardDTO bdto) {
		log.info(">>>>> customer modify service impl>>>>>");
		
		int isOk = cmbdao.modify(bdto.getCmbvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		} else {
			if(isOk > 0 && bdto.getFlist().size() >0) {
				long cmBno = bdto.getCmbvo().getCmBno();
				
				for(cmFileVO cmfvo : bdto.getFlist()) {
					cmfvo.setCmBno(cmBno);
					isOk = cmfdao.insertFile(cmfvo);
					
				}
			}
		}
		return isOk;
	}

	@Override
	public int delete(long cmbno) {
		log.info(">>>>> customer delete service impl >>>>>");
		return cmbdao.delete(cmbno);
	}

	@Override
	public int removeFile(String cmUuid) {
		log.info(">>>>> removeFile service impl >>>>>");
		return cmfdao.removeFile(cmUuid);
	}


}
