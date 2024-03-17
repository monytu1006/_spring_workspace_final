package com.myfinal.www.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myfinal.www.domain.cmBoardDTO;
import com.myfinal.www.domain.cmBoardVO;
import com.myfinal.www.domain.cmFileVO;
import com.myfinal.www.domain.nmPagingVO;
import com.myfinal.www.handler.cmFileHandler;
import com.myfinal.www.handler.nmPagingHandler;
import com.myfinal.www.service.CustomerManagerBoardServcie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cmboard/*")
public class CustomeManegerBoardController {
	
	private final CustomerManagerBoardServcie bsv;
	
	private final cmFileHandler cmfhd;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String insert(cmBoardVO cmbvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>> cmbvo >>>> {}", cmbvo);
		List<cmFileVO> flist = null;
		
		if(files[0].getSize() > 0) {
			flist = cmfhd.uploadFiles(files);
			log.info(">>>>>>>>flist>> {}",flist);
		} else {
			log.info("file null");
		}
		
		cmBoardDTO bdto = new cmBoardDTO(cmbvo, flist);
		int isOk = bsv.insert(bdto);
		
		log.info(">>>>> cmboard register >>>>>" + ((isOk > 0) ? "OK" : "Fail"));
		
		return "redirect:/cmboard/list";
	}
	
	@GetMapping("/list")
	public void list(Model m, nmPagingVO pgvo) {
		List<cmBoardVO> list = bsv.getList(pgvo);
		int totalCount = bsv.getTotalCount(pgvo);
		
		nmPagingHandler ph = new nmPagingHandler(pgvo, totalCount);
		m.addAttribute("list", list);
		m.addAttribute("ph", ph);
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model m, @RequestParam("cmbno") long cmbno) {
		log.info(">>>> cmbno >>"+cmbno);
		m.addAttribute("bdto", bsv.getDetail(cmbno));
	}
	
	@PostMapping("/modify")
	public String modify(cmBoardVO cmbvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>>>> cmbvo >>>>> {}", cmbvo);
		
		List<cmFileVO> flist = null;
		
		if(files[0].getSize()>0) {
			flist = cmfhd.uploadFiles(files);
			log.info(">>>>> flist >>>>> {}" ,flist);
		} else{
			log.info("파일이 없습니다");
		}
		
		cmBoardDTO bdto = new cmBoardDTO(cmbvo, flist);
		
		int isOk = bsv.modify(bdto);
		
		log.info("modify is " + ((isOk >0)? "OK" : "Fail"));

		return "redirect:/cmboard/detail?cmbno=" + cmbvo.getCmBno();
	}
	
	@GetMapping("/remove")
	public String delete(@RequestParam("cmbno") long cmbno ) {
		log.info(">>>>> cmbno >>>>> {}"+ cmbno);
		int isOk = bsv.delete(cmbno);
		log.info(">>>> delete>>>> is" + (isOk > 0? "OK" : "Fail"));
		
		return "redirect:/cmboard/list";
	}
	
	@DeleteMapping("/{cmUuid}")
	public ResponseEntity<String> removeFile(@PathVariable("cmUuid")String cmUuid){
		log.info(">>>>> cmUuid>>>>> {}", cmUuid);
		int isOk = bsv.removeFile(cmUuid);
		
		log.info(">>>>> delete is " + (isOk > 0? "OK" : "Fail"));
		
		return (isOk > 0) ? 
				new ResponseEntity<String>("1", HttpStatus.OK):
				new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
