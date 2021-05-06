package com.reshome.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.reshome.dto.Criteria;
import com.reshome.service.UserProductService;

import lombok.Setter;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Setter(onMethod_ = @Autowired)
	private UserProductService userProductService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	// 새로 등록된 상품 순으로 보여주는
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(@ModelAttribute("cri") Criteria cri, Date gds_date_reg, Model model) throws Exception{
		
		cri.setAmount(8); // 한 페이지에 뿌리고자 하는 상품출력 개수설정.
		
		model.addAttribute("newProductVOList", userProductService.getProductListNew(cri, gds_date_reg));
		
		return "/main2";
	}
	
}
