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
public class cmBoardVO {
//	create table cmboard(
//			cm_bno bigint not null auto_increment,
//			cm_category varchar(200) not null,
//			cm_title varchar(200) not null,
//			cm_writer varchar(100),
//			cm_content text not null,
//			cm_reg_at datetime default now(),
//			cm_mod_at datetime default now(),
//			cm_read_count int default 0,
//			cm_cmt_qty int default 0,
//			cm_has_file int default 0,
//			primary key(cm_bno));
	
	private long cmBno;
	private String cmCategory;
	private String cmTitle;
	private String cmWriter;
	private String cmContent;
	private String cmRegAt;
	private String cmModAt;
	private int cmReadCount;
	private int cmCmtQty;
	private int cmHasFile;
}
