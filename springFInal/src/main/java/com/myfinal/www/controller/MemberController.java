package com.myfinal.www.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfinal.www.security.MemberVO;
import com.myfinal.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private final MemberService msv;
	
	private final BCryptPasswordEncoder bcEncoder;
	
	@GetMapping("/register")
	public String register() {
		return "/member/register";
	}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info(">>>>> register >>>>> {}" + mvo);
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		
		int isOk = msv.register(mvo);
		log.info(">>>>>>>>> login register is"+(isOk>0?"OK":"Fail"));
		
		return "index";
	}
	
	@GetMapping(value="/{email}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> checkEmail(@PathVariable("email") String email){
		log.info(">>>>> email >>>>> {}", email);
		
		MemberVO mvo = msv.checkEmail(email);
		
		return (mvo == null)? 
			new ResponseEntity<String>("1", HttpStatus.OK):
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/login")
	public void login() {}

	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		
		return "redirect:/member/login";
	}

	
	@GetMapping({"/detail", "/modify"})
	public void detail() {
		
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes re) {
		log.info(">>>>> mvo >>>>> {}"+mvo);
		
		if(mvo.getPwd().length() > 0) {
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		}else {
			String email = mvo.getEmail();
			mvo.setPwd(msv.getPwd(email));
		}
		
		int isOk = msv.modify(mvo);
		re.addFlashAttribute("modify_msg", "1");
		
		logout(request, response);
		
		return "redirect:/member/login";
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		List<MemberVO> list = msv.getList();
		
		m.addAttribute("list", list);
	}
	
	@Transactional
	@GetMapping("/delete")
	public String removeMember(Principal p, RedirectAttributes re, HttpServletRequest request, HttpServletResponse reponses) {
		String email = p.getName();
		int isOk = msv.remove(email);
		
		if(isOk > 0) {
			re.addFlashAttribute("delete_msg", "1");
			logout(request, reponses);
		}
		return "redirect:/member/login";
	}
	
	
	@GetMapping("/registerBlackList")
	public String registerBlackList(MemberVO mvo) {
		log.info(">>>>> registerBlackList >>>>> {}" + mvo);
		
		int isOk = msv.registerBlackList(mvo);
		log.info(">>>>>>>>> registerBlackList is"+(isOk>0?"OK":"Fail"));
		
		return "index";
	}
	@GetMapping("/cancelBlackList")
	public String cancelBlackList(MemberVO mvo) {
		log.info(">>>>> registerBlackList >>>>> {}" + mvo);
		
		int isOk = msv.cancelBlackList(mvo);
		log.info(">>>>>>>>> cancelBlackList is"+(isOk>0?"OK":"Fail"));
		
		return "index";
	}
	
	
	
	private void logout(HttpServletRequest request, HttpServletResponse responses) {
		Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, responses, authentication);
	}
}