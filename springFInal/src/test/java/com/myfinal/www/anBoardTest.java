package com.myfinal.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myfinal.www.domain.anBoardVO;
import com.myfinal.www.repository.AnonymousBoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myfinal.www.config.RootConfig.class})
public class anBoardTest {
	
	@Inject
	private AnonymousBoardDAO anbdao;
	
	@Test
	public void insertBoard() {
		for(int i=0; i<300; i++) {
			anBoardVO bvo = new anBoardVO();
			bvo.setAnTitle("Test title" + i);
			bvo.setAnContent("test Content" + i + "입니다");
			anbdao.insert(bvo);
		}
	}
	
}