package com.reshome.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	private static final String LOGIN = "loginStatus";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
			HttpSession session = request.getSession();
			
			// 인증체크작업
			if(session.getAttribute(LOGIN) == null) { // null이면 인증이 안되어있다 판단
				
				logger.info("로그인이 안되어있습니다.");
				
				targetSave(request);
				
				if(isAjaxRequest(request)) {
					response.sendError(-1);
					return false;
				}else {
				
				response.sendRedirect("/member/login"); // /member/login -> /member/loginPost(인터셉터 설정)
				return false; // Controller로 제어가 안넘어온다.
				}
			}
	
			return true; // Controller로 제어가 넘어간다
		}
	
		// ajax요청 체크
		private boolean isAjaxRequest(HttpServletRequest req) {
			String header = req.getHeader("AJAX");
			if("true".equals(header)) {
				return true;
			}else {
				return false;
			}
			
		}
	
	// 세션이 소멸된 상태이거나 비로그인시 요청한 주소를 저장
	// 사용자가 로그인이 진행이되면, 요청한 주소가 있으면 그곳으로 이동시켜준다. 없으면 루트로 이동
	private void targetSave(HttpServletRequest request) {
		
		// /member/modify?userid=doccomsa
		String uri = request.getRequestURI();	// 사용자가 요청한 주소
		String queryString = request.getQueryString();	
		
		if(queryString == null || queryString.equals("null")) {
			queryString = "";
		}else {
			queryString = "?" + queryString;		// 위 주소의 ? 를 수동처리한 것
		}
		
		if(request.getMethod().equals("GET")) {
			logger.info("targetSave: " + (uri + queryString));
			request.getSession().setAttribute("targetUrl", uri + queryString);
		}
	}

}
