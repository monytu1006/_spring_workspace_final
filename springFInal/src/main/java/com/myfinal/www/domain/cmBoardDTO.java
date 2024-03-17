package com.myfinal.www.domain;

import java.util.List;

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
public class cmBoardDTO {
	
	private cmBoardVO cmbvo;
	
	private List<cmFileVO> flist;
}
