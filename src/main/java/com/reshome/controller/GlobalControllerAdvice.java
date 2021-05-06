package com.reshome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.reshome.service.UserProductService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ControllerAdvice(basePackages = {"com.reshome.controller"})
@Log4j
public class GlobalControllerAdvice {
	
	@Setter(onMethod_ = @Autowired)
	private UserProductService userProductSerivce;
	
	@ModelAttribute
	public void CategoryList(Model model) throws Exception{
		
		log.info("톰캣서비스 시작....");
		
		model.addAttribute("mainCateList", userProductSerivce.getCategoryList());
	}

}
