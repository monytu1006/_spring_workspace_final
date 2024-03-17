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
public class nmCommentVO {

	/*
create table nmcomment(
nm_cno bigint auto_increment,
nm_bno bigint not null,
nm_writer varchar(100) not null,
nm_content text not null,
nm_reg_at datetime default now(),
nm_mod_at datetime default now(),
primary key(nm_cno)
);
	 */
	
	private long nmCno;
	private long nmBno;
	private String nmWriter;
	private String nmContent;
	private String nmRegAt;
	private String nmModAt;
	
	
}
