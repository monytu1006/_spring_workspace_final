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
public class cmCommentVO {

	/*
create table cmcomment(
cm_cno bigint auto_increment,
cm_bno bigint not null,
cm_writer varchar(100) not null,
cm_content text not null,
cm_reg_at datetime default now(),
cm_mod_at datetime default now(),
primary key(cm_cno)
);
	 */
	
	private long cmCno;
	private long cmBno;
	private String cmWriter;
	private String cmContent;
	private String cmRegAt;
	private String cmModAt;
	
	
}
