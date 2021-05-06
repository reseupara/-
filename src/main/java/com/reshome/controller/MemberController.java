package com.reshome.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reshome.domain.MemberVO;
import com.reshome.dto.EmailDTO;
import com.reshome.dto.LoginDTO;
import com.reshome.service.EmailService;
import com.reshome.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//회원관련 기능
@Controller
@Log4j
@RequestMapping("/member/*")
public class MemberController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@Setter(onMethod_ = @Autowired)
	private EmailService maileService;
	
	@Inject
	private BCryptPasswordEncoder cryPassEnc;
	
	
	// 로그인 주소
	@GetMapping("/login")
	public void login() {
		
		log.info("로그인");
	
	}
	
	
	
	// 로그인작업, 로그인 인증
	@PostMapping(value = "/loginPost")
	public void login_ok(LoginDTO dto, RedirectAttributes rttr, HttpSession session, Model model) throws Exception {
		
		MemberVO vo = service.login_ok(dto);
		
		if(vo == null) return;
		
		String result = "loginIDFail";
		
		if(vo != null) {
			
			
			if(cryPassEnc.matches(dto.getMbme_pw(), vo.getMbme_pw())) {
				//vo.setMbme_pw("");
				// session.setAttribute("loginStatus", vo); // 세션정보로 인증상태를 저장
				
				// 인터셉터에서 참조할 모델작업
				model.addAttribute("memberVO", vo);
				
				
				
				result = "loginSuccess";
			}else {
				result = "loginPWFail";
				return;
			}
		}
		
		rttr.addFlashAttribute("status", result); // main.jsp에서 쓰는 애들

	}
	
	// 로그아웃 기능 -> 작업 후 메인페이지("/")로 이동
	@GetMapping("/logout")
	// 작업 후 주소 이동될 것 같다면 String. 아니면 void
	public String logout(HttpSession session, RedirectAttributes rttr) {
		
		log.info("logout");
		
		session.invalidate();
		
		String result = "logout";
		rttr.addFlashAttribute("status", result);
		
		return "redirect:/";
	}
	
	
	// 회원가입 주소
	@GetMapping("/join")
	public void join() {
		
		log.info("회원가입");
			
	}
	
	// 회원가입
	@PostMapping("/join")
	public String join(MemberVO vo, RedirectAttributes rttr) throws Exception {
		
		log.info(vo);
		
		vo.setMbme_pw(cryPassEnc.encode(vo.getMbme_pw()));
		
		String result = "";
		service.join(vo);
		
		result = "insertSuccess";
		
		rttr.addFlashAttribute("status", result);
		
		return "redirect:/";
	}
	
	// 회원삭제
	@GetMapping("/delete")
	public String delete(HttpSession session, RedirectAttributes rttr) throws Exception{
		
		log.info("delete");
		
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id();
		
		service.deleteMb(mbme_id);
		session.invalidate();
		
		String result = "regdelete";
		
		rttr.addFlashAttribute("status", result); // 루트(/)주소로 이동시 main.jsp에서 참조
		
		return "redirect:/";

	}
	
	
	
	// 아이디중복체크
	@ResponseBody
	@RequestMapping(value = "checkIdOverlap", method=RequestMethod.POST)
	public ResponseEntity<String> checkIdOverlap(@RequestParam("mbme_id") String mbme_id) throws Exception {
		
		log.info("========checkIdOverlap execute()....");
		ResponseEntity<String> entity = null;
		try {
			int count = service.checkIdOverlap(mbme_id); // count가 0이면 아이디 사용가능, 1d면 사용 불가능.
			
			if(count != 0) { // 아이디가 존재해서 사용 불가능할 경우
				entity = new ResponseEntity<String>("Fail", HttpStatus.OK);
			}else { // 아이디 사용 가능한 경우
				entity = new ResponseEntity<String>("Success", HttpStatus.OK);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST); // 요청에 문제가 있을 때
		}
		
		return entity;
	}
	
	
	// 회원수정폼 전 인증단계
	@GetMapping("/modifycheck")
	public void modifycheck(HttpSession session, Model model) throws Exception {
		
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id();
		model.addAttribute("vo", service.member_info(mbme_id));
	}
	
	
	@PostMapping("/modifycheck")
	public String modify_check(LoginDTO dto, RedirectAttributes rttr, HttpSession session, Model model) throws Exception {
		
		MemberVO vo = service.login_ok(dto);
		
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id();
		vo.setMbme_id(mbme_id);
		
		
		if(cryPassEnc.matches(dto.getMbme_pw(), vo.getMbme_pw())) {
			log.info("기능됨----------------");
			session.setAttribute("modifyStatus", vo);
			return "redirect:/member/modify";
			
		}else {
			log.info("비번틀림--------");
			return "redirect:/member/modifycheck";
		}
	}
	
	
	// 회원수정 폼 : db에서 회원정보를 가져와서 출력
	@GetMapping("/modify")
	// 주소와 jsp 파일명이 같게하려면 void, 다르게하려면 String
	// 데이터베이스와 관계되는 작업을 하는 메서드를 나타낼 때는 예외전가를 하는 throws Exception가 필요.
	public void reg_edit(HttpSession session, Model model) throws Exception {
		
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id();
		((MemberVO) session.getAttribute("modifyStatus")).getMbme_id();
		/*
		MemberVO vo = service.member_info(mbme_id);
		model.addAttribute("vo", vo);
		아래는 한꺼번에 처리하는 방법. */
		
		// 키를 생략한 스타일. 키를 생략하면 리턴타입이 키값으로 만들어지는데 소문자로.
//		model.addAttribute(service.member_info(mbme_id));
		// jsp에 전달되는 데이터의 키는? MemberVO에서 소문자로 바뀌어 memberVO로 전달
		
		// 키를 지정한 스타일
		// vo 라는 이름으로 modify.jsp에서 사용.
		model.addAttribute("vo", service.member_info(mbme_id));
		
		
		session.removeAttribute("modifyStatus");
		
	}
	
	// 회원수정 작업
	// post방식이라 위의 get방식과 같아도 상관 x
	@PostMapping("/modify")
	public String modifyPost(MemberVO vo, RedirectAttributes rttr, HttpSession session) throws Exception{
		
		String result = "";
		
		// 선택적으로 하고싶으면 boolean 사용.
//		if(service.modifyPOST(vo) == true) {
//			result = "modifySuccess";
//		}else {
//			result = "modifyFail";
//		}
		
		// 로그인시 세션에서 아이디를 참고
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id();
		vo.setMbme_id(mbme_id);
		
		// MemberVO vo에 회원수정폼에서 아이디 파라미터가 존재한 경우
		
		// 노멀한 사용방법.
		service.modifyPOST(vo);
		result = "modifySuccess";
		
		rttr.addFlashAttribute("status", result);
		
		
				
		return "redirect:/";
	}
	
	// 아이디 찾기 폼
	@GetMapping("/find_id")
	public void find_id() {
		
		log.info("find_id");
		
	}
	
	// 아이디 찾기 기능 : 화면출력
	@PostMapping("/find_id")
	public ResponseEntity<String> find_id(@RequestParam("mbme_name") String mbme_name) throws Exception{
		
		log.info("아이디?" + mbme_name);
		
		ResponseEntity<String> entity = null;
		
		String mbme_id = service.find_id(mbme_name);
		
		if(mbme_id != null) {
			entity = new ResponseEntity<String>(mbme_id, HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}
		
		return entity;
		
	}
	
	
	// 비밀번호 찾기 폼
	@GetMapping("/find_pwd")
	public void find_pwd() {
		
		log.info("fine_pwd");
		
	}
	
	// 비밀번호 찾기 기능(ajax 적용)
	// @ResponseBody 리턴되는 결과값을 클라이언트 패킷의 Body에 집어넣는 역할.
	@ResponseBody
	@PostMapping("/find_pwd")
	public ResponseEntity<String> find_pwd(@RequestParam("mbme_id") String mbme_id, @RequestParam("mbme_name") String mbme_name, EmailDTO dto) throws Exception {
		
		log.info("아이디?" + mbme_id);
		log.info("이름?" + mbme_name);

		
		ResponseEntity<String> entity = null;
		
		MemberVO vo = service.find_pwd(mbme_id, mbme_name);
		
		if(vo != null) {
			
			// 메일발송작업
			dto.setReceiveMail(vo.getMbme_email());
			dto.setSubject("요청하신 비밀번호입니다.");
			dto.setMessage(mbme_id + " 님의 비밀번호입니다.");
			
			maileService.sendMail(dto, mbme_name);
			
			entity = new ResponseEntity<String>("Success", HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}
		
		return entity;
	}
	
	
	
	// 로그아웃 기능
	
	
	
	
	
	
	

}
