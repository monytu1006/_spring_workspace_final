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
public class nmBoardVO {
//	create table nmboard(
//			nm_bno bigint not null auto_increment,
//			nm_title varchar(200) not null,
//			nm_writer varchar(100) not null,
//			nm_content text not null,
//			nm_reg_at datetime default now(),
//			nm_mod_at datetime default now(),
//			nm_read_count int default 0,
//			nm_cmt_qty int default 0,
//			nm_has_file int default 0,
//			primary key(nm_bno));
	
	private long nmBno;
	private String nmTitle;
	private String nmWriter;
	private String nmContent;
	private String nmRegAt;
	private String nmModAt;
	private int nmReadCount;
	private int nmCmtQty;
	private int nmHasFile;
	

}
