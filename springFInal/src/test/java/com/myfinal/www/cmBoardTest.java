package com.myfinal.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myfinal.www.domain.cmBoardVO;
import com.myfinal.www.repository.CustomerManagerBoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myfinal.www.config.RootConfig.class})
public class cmBoardTest {
	
	@Inject
	private CustomerManagerBoardDAO cmbdao;
	
	@Test
	public void insertBoard() {
		for(int i=0; i<300; i++) {
			cmBoardVO bvo = new cmBoardVO();
			bvo.setCmCategory("게정문의" + i);
			bvo.setCmTitle("Test title" + i);
			bvo.setCmWriter("Tester");
			bvo.setCmContent("test Content" + i + "입니다");
			cmbdao.insert(bvo);
		}
	}
	
}