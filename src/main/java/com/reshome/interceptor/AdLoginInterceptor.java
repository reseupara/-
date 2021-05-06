package com.reshome.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdLoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AdLoginInterceptor.class);
	private static final String LOGIN = "adLoginStatus";
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) == null) {
			
				targetSave(request);
				
				response.sendRedirect("/admin/");
				return false;
				
			}

		return true;
	}
	
	// 세션이 소멸된 상태이거나 비로그인시 요청한 주소를 저장
	// 사용자가 로그인이 진행이되면, 요청한 주소가 있으면 그곳으로 이동. 없으면 루트로 이동. 
	private void targetSave(HttpServletRequest request) {
		
		// /member/modify?userid=doccomsa
		String uri = request.getRequestURI();
		String queryString = request.getQueryString(); 
		
		if(queryString == null || queryString.equals("null")) {
			queryString = "";
		}else {
			queryString = "?" + queryString;
		}
		
		if(request.getMethod().equals("GET")) {
			logger.info("targetSave: " + (uri + queryString));
			request.getSession().setAttribute("targetUrl", uri + queryString);
		}
		
	}
				
		
		
		
	

}
