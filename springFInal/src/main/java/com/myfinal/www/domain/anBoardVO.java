package com.myfinal.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class anBoardVO{
	
//	create table anboard(
//	an_bno bigint not null auto_increment,
//	an_title varchar(100) not null,
//	an_content text not null,
//	an_reg_at datetime default now(),
//	an_mod_at datetime default now(),
//	an_read_count int default 0,
//	primary key(an_bno));
	
//	writer, cmt, hasfile 제외
	
	private long anBno;
	private String anTitle;
	private String anContent;
	private String anRegAt;
	private String anModAt;
	private int anReadCount;

	
}
