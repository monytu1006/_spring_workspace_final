package com.myfinal.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myfinal.www.domain.nmBoardVO;
import com.myfinal.www.repository.NormalBoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myfinal.www.config.RootConfig.class})
public class nmBoardTest {
	
	@Inject
	private NormalBoardDAO nmbdao;
	
	@Test
	public void insertBoard() {
		for(int i=0; i<300; i++) {
			nmBoardVO bvo = new nmBoardVO();
			bvo.setNmTitle("Test title" + i);
			bvo.setNmWriter("Tester");
			bvo.setNmContent("test Content" + i + "입니다");
			nmbdao.insert(bvo);
		}
	}
	
}