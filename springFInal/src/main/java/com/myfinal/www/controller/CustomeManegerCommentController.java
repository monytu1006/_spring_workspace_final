package com.myfinal.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfinal.www.domain.cmCommentVO;
import com.myfinal.www.domain.cmPagingVO;
import com.myfinal.www.handler.cmPagingHandler;
import com.myfinal.www.service.CustomerManageCommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/cmcomment/*")
@RestController
@RequiredArgsConstructor
public class CustomeManegerCommentController {
	private final CustomerManageCommentService csv;
	
	@PostMapping(value="/post", consumes="application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody cmCommentVO cmcvo){
		log.info(" >>>>> cmcvo >>>>> {} "+cmcvo);
		
		int isOk = csv.post(cmcvo);
		
		return (isOk > 0)? 
			new ResponseEntity<String>("1", HttpStatus.OK):
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{cmBno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<cmPagingHandler> list(@PathVariable("cmBno")long cmBno, @PathVariable("page") int page){
		log.info(">>>>> cmBno>>{}"+cmBno + ">>>>> page>>{}"+page);
		cmPagingVO pgvo = new cmPagingVO(page,5);
		cmPagingHandler ph= csv.getList(cmBno,pgvo);
		return new ResponseEntity<cmPagingHandler>(ph, HttpStatus.OK);
	}
	
	@PutMapping(value = "/edit", consumes="application/json", produces =MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@RequestBody cmCommentVO cmcvo){
		log.info(">>>>>>  cmcvo >>>>> " + cmcvo);
		int isOk = csv.edit(cmcvo);
		return isOk > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) :
				new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/delete/{cmCno}/{cmWriter}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> delete(@PathVariable("cmCno")long cmCno, @PathVariable("cmWriter")String cmWriter){
		log.info(">>>>>>  cmCno >>>>> " + cmCno);
		int isOk = csv.delete(cmCno);
			
		return isOk> 0 ?
				new ResponseEntity<String>("1",HttpStatus.OK):
				new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
