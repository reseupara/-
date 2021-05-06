package com.reshome.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.log4j.Log4j;

// 인터셉터들은 추상메서드다
@Log4j
public class SampleInterceptor extends HandlerInterceptorAdapter {
	
	// 인터셉터 기능을 갖는 클래스를 만들려면, HandlerInterceptorAdapter 추상클래스를 상속받아야한다. (전제조건)

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		log.info("preHandle...");
		System.out.println("preHandle....");

		return true;
		// 리턴이 true여야 컨트롤러로 간다.
		// false면 여기서 끝. 컨트롤러로 제어가 넘어가지 않음
	}

	// 컨트롤러 갔다가 여기(postHandle)로 온다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

//		log.info("postHandle...");
		System.out.println("postHandle....");
		

		super.postHandle(request, response, handler, modelAndView);
	}
	
	
}
