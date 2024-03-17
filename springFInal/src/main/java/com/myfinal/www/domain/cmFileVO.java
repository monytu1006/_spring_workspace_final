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
public class cmFileVO {

	
	/*
create table cmfile(
cm_uuid varchar(256) not null,
cm_save_dir varchar(256) not null,
cm_file_name varchar(256) not null,
cm_file_type tinyint(1) default 0,
cm_bno bigint,
cm_file_size bigint,
cm_reg_at datetime default now(),
primary key(cm_uuid));
	*/
	
	private String cmUuid;
	private String cmSaveDir;
	private String cmFileName;
	private int cmFileType;
	private long cmBno;
	private long cmFileSize;
	private String cmRegAt;
	
	
	
}
