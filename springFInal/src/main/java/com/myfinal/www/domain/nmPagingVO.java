package com.myfinal.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class nmPagingVO {
	
	private int pageNo;
	private int qty;
	
	private String type;
	private String keyword;
	
	public nmPagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public nmPagingVO(int pageNo, int qty) {
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
