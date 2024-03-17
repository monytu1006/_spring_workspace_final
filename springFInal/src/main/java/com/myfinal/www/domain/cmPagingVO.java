package com.myfinal.www.domain;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Getter
@Setter
@ToString
public class cmPagingVO {

	private int pageNo;
	private int qty;
	
	private String type;
	private String keyword;
	
	public cmPagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public cmPagingVO(int pageNo, int qty) {
		this.pageNo= pageNo;
		this.qty = qty;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*qty;
	}
	
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
}
