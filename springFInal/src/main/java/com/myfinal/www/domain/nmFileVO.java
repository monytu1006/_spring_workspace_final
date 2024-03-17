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
public class nmFileVO {

	
	/*
	create table nmfile(
			nm_uuid varchar(256) not null,
			nm_save_dir varchar(256) not null,
			nm_file_name varchar(256) not null,
			nm_file_type tinyint(1) default 0,
			nm_bno bigint,
			nm_file_size bigint,
			nm_reg_at datetime default now(),
			primary key(nm_uuid));
	*/
	
	private String nmUuid;
	private String nmSaveDir;
	private String nmFileName;
	private int nmFileType;
	private long nmBno;
	private long nmFileSize;
	private String nmRegAt;
	
	
	
}
