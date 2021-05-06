package com.reshome.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 인증처리작업 : HttpSession 로그인생성
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	private static final String LOGIN = "loginStatus";
	
	
	// Object handler : URL Mapping주소에 해당하는 메서드를 가리키는 정보 자체를 가리킴
	// 재정의 안하면 부모쪽 preHandle이 작동된다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}

	// 컨트롤러의 매핑주소- /member/loginPost(메서드호출) -> postHandle 메서드 -> 뷰(jsp)화면처리 작업이 진행됨(loginPost.jsp).
	// ModelAndView : (Model + View) 두가지를 합친 클래스
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		/*
		MemberVO vo = new MemberVO();
		modelAndView.setViewName("member.jsp");
		modelAndView.addObject("MemberVO", vo);
		*/
		
		// 로그인 인증처리하기 위한 세션객체 확보
		HttpSession session = request.getSession();
		
		// 로그인시 Model정보를 참조하는 작업
		ModelMap modelMap = modelAndView.getModelMap();
		Object memberVO = modelMap.get("memberVO");
		
		if(memberVO != null) {		// false일 때 사용되기 때문에 loginPost.jsp가 필요하다.
			
			logger.info("로그인 성공");
			session.setAttribute(LOGIN, memberVO);
			
			Object targetUrl = session.getAttribute("targetUrl");
			
			response.sendRedirect(targetUrl != null ? (String) targetUrl : "/");
		}
		
		// response.sendRedirect("/"); 이 위치에서는 지원이 안됨.
		// ajax요청시 인터셉터는 정상작동 되지 않음.
		
	}
	
	
}
