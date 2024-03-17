package com.myfinal.www.handler;

import java.util.List;

import com.myfinal.www.domain.nmCommentVO;
import com.myfinal.www.domain.nmPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class nmPagingHandler {

	private int startPage;
	private int endPage;
	private Boolean prev, next;
	
	private int totalCount;
	private nmPagingVO pgvo;
	
	private List<nmCommentVO> cmtList;
	
	public nmPagingHandler(nmPagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)pgvo.getQty())*pgvo.getQty();
		this.startPage = endPage - 9;
		
		
		int realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
	
	public nmPagingHandler(nmPagingVO pgvo, int totalCount, List<nmCommentVO> cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
		
}
