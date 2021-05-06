package com.reshome.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reshome.domain.AdminVO;
import com.reshome.service.AdminService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/*")
public class AdminController {
	
	@Setter(onMethod_ = @Autowired)
	private AdminService adminService;
	
	@GetMapping("")
	public String admin_main() {
		
		return "/admin/admin_login";
		
	}
	
	@PostMapping("/admin_check")
	public String admin_ok(AdminVO vo, HttpSession session, RedirectAttributes rttr, Model model) throws Exception {
		
		AdminVO adVO = null;
		
		adVO = adminService.login_check(vo);
		
		if(adVO == null) {
			
			rttr.addFlashAttribute("msg", "Fail");
			return "redirect:/admin/";
		}else {
						// MemberController(사용자 로그인)쪽의 session명과 같아서는 안된다.
		// 세션인증작업
		session.setAttribute("adLoginStatus", adVO);
		//model.addAttribute("adLoginStatus", vo);
		
		return "redirect:/admin/admin_process";
		}
	}
	
	@GetMapping("/admin_process")
	public String amdin_process(HttpSession session) {
		
		log.info("admin_process");
		
		String url = "";
		
		if(session.getAttribute("adLoginStatus") == null) {
			url = "redirect:/admin/"; // 관리자로그인 주소
		}else {
			url = "/admin/admin_process"; // 관리자 메뉴 뷰(jsp)
		}
		// redirect가 사용하면 주소의미, 사용하지 않으면 jsp파일명
		return url;	
		
	}
	
	@PostMapping("/admin_logout")
	public String admin_logout(HttpSession session, RedirectAttributes rttr) {
		
		session.invalidate();
		
		rttr.addFlashAttribute("msg", "logout");
		return "redirect:/admin/";
	}

}
