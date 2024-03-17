package com.myfinal.www.handler;

import java.util.List;

import com.myfinal.www.domain.cmCommentVO;
import com.myfinal.www.domain.cmPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class cmPagingHandler {
	
	private int startPage;
	private int endPage;
	private Boolean prev, next;
	
	private int totalCount;
	private cmPagingVO pgvo;
	
	private List<cmCommentVO> cmtList;
	
	public cmPagingHandler(cmPagingVO pgvo, int totalCount) {
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
	
	public cmPagingHandler(cmPagingVO pgvo, int totalCount, List<cmCommentVO> cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
}
