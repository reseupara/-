package com.reshome.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reshome.domain.CartVO;
import com.reshome.domain.CartVOList;
import com.reshome.domain.MemberVO;
import com.reshome.dto.CartDTO;
import com.reshome.service.CartService;
import com.reshome.util.FileUploadUtils;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cart/*") // 공통주소로 사용 또는 jsp폴더명
public class CartController {
	
	@Setter(onMethod_ = @Autowired)
	private CartService cartService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@ResponseBody
	@PostMapping("/add")
	public ResponseEntity<String> cart_add(long gds_num_pk, int gds_amount, HttpSession session, HttpServletResponse response) throws Exception{
		
//		log.info("cart_add" + gds_num_pk);
//		log.info("cart_add" + gds_amount);
		ResponseEntity<String> entity = null;
		
		if(session.getAttribute("loginStatus") == null) { // null이면 인증이 안되어있다 판단
			entity = new ResponseEntity<String>("LoginRequired", HttpStatus.OK);
			
			return entity;
			
		}
		
		
		//loginStatus
		
		MemberVO vo = (MemberVO) session.getAttribute("loginStatus");
		
		CartVO cart = new CartVO(0, gds_num_pk, vo.getMbme_id(), gds_amount);
		
		log.info("cart_add: " + cart);
		
		try {
			cartService.add_cart(cart);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}
	
	
	@GetMapping("/cart_list")
	public void cart_list(HttpSession session, Model model) throws Exception{
		
		log.info("cart_list");
		
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id();
		List<CartVOList> cartList = cartService.list_cart(mbme_id);
		
		log.info(cartList.size());
		model.addAttribute("cartVOList", cartList);
		
	}
	
	// 상품이미지 뷰
	@ResponseBody
	@RequestMapping(value = "/displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		
		return FileUploadUtils.getFile(uploadPath, fileName);
	}
	
	@ResponseBody
	@PostMapping("/delete")
	public ResponseEntity<String> cart_delete(long cart_code_pk) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		try {
			cartService.cart_delete(cart_code_pk);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/delete_all")
	public String cart_all_delete(HttpSession session) throws Exception{
		
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id();
		cartService.cartAll_delete(mbme_id);
		
		return "redirect:/cart/cart_list";
	}
	
	@ResponseBody
	@PostMapping("/cart_check_delete")
	public ResponseEntity<String> cart_check_delete(@RequestParam("checkArr[]") List<Integer> checkArr) throws Exception{
		
		log.info("cart_check_delete: " + checkArr);
		
		ResponseEntity<String> entity = null;
		
		try {
			cartService.cart_check_delete(checkArr);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@ResponseBody
	@PostMapping("/cart_check_order")
	public ResponseEntity<String> cart_check_order(@RequestParam("checkArr[]") List<Integer> checkArr) throws Exception{
		
		log.info("cart_check_order: " + checkArr);
		
		ResponseEntity<String> entity = null;
		
		try {
			cartService.cart_not_check_delete(checkArr);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@ResponseBody
	@PostMapping("/cart_amount_update")
	public ResponseEntity<String> cart_amount_update(int cart_code_pk, int cart_amount) throws Exception{

		ResponseEntity<String> entity = null;
		
		try {
			cartService.cart_amount_update(cart_code_pk, cart_amount);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}
	
	@GetMapping("/chart_sample")
	public void chart_sample(Model model) {
		
		List<CartDTO> items = new ArrayList<CartDTO>();	// DB에서 호출하는 부분
		
		Random random = new Random();
		
		for(int i=1; i<=5; i++) {
			CartDTO cart = new CartDTO();
			int price = random.nextInt(10000 - 1000 + 1) + 1000;
			cart.setAmount(price);
			cart.setGds_name("전자제품" + i);
			
			items.add(cart);				
		}
		
		/*
		 pip chart sample data
		 [
		  ['상품명', '가격'],
          ['전자제품1', 15000],
          ['전자제품2', 25000],
          ['전자제품3', 18000],
          ['전자제품4', 16500],
          ['전자제품5', 35000]
        ]
		 
		 */
		
		int num = 0;
		String str = "[";
		str += "['상품명', '가격'],";
		for(CartDTO dto : items) {
			str += "['";
			str += dto.getGds_name();
			str += "',";
			str += dto.getAmount();
			str += "]";
			
			num++;
			if(num<items.size()) str += ",";
		}
		
		str += "]";
		
		log.info(str);
		
		model.addAttribute("chartData", str);
		
	}


}
