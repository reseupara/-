package com.reshome.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reshome.domain.CartVOList;
import com.reshome.domain.MemberVO;
import com.reshome.domain.OrderDetailVO;
import com.reshome.domain.OrderVO;
import com.reshome.domain.ProductVO;
import com.reshome.service.CartService;
import com.reshome.service.OrderService;
import com.reshome.service.UserProductService;
import com.reshome.util.FileUploadUtils;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/order/*")
public class OrderController {
	
	@Setter(onMethod_ = @Autowired)
	private CartService cartService;
	
	@Setter(onMethod_ = @Autowired)
	private OrderService orderService;
	
	@Setter(onMethod_ = @Autowired)
	private UserProductService userProductService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	

	// @RequestParam(required = false) : get 요청에 의한 해당쿼리스트링이 존재하지 않아도 처리하고자 할 때 사용. (예외발생이 안됨)
	
	// 1)즉시구매에서 보여주는 작업 type=1 2)장바구니 통하여 구매시 보여주는 작업 type=2
	@RequestMapping(value = "/order", method = {RequestMethod.GET, RequestMethod.POST})
	public void order(HttpSession session, @ModelAttribute("type") String type, @RequestParam(required = false) Long gds_num_pk, @RequestParam(required = false) Integer odr_amount, Model model) throws Exception{
		
		// type : 1- 즉시구매 (장바구니 사용안함), 2 - 장바구니 기반으로 주문하기
		
		// 사용자별 장바구니 내역
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id();
		
		if(type.equals("1")) {
			// 즉시구매
			log.info("즉시구매" + type);
			
			// 구매상품에 대한 정보를 표시
			
			ProductVO vo = userProductService.getProductByNum((long)gds_num_pk);
			
			// 매개변수가 있는 생성자를 만들어서, 디폴트 생성자 메서드
			CartVOList cartvo = new CartVOList(0, vo.getGds_img(), vo.getGds_name(), (int)odr_amount, vo.getGds_price());
			
			List<CartVOList> cartvoList = new ArrayList<CartVOList>();
			cartvoList.add(cartvo);
			
			model.addAttribute("cartVOList", cartvoList);
			
			// 즉시구매시 상품상세정보 폼에 삽입
			model.addAttribute("gds_num_pk", gds_num_pk);
			model.addAttribute("odr_amount", odr_amount);
			model.addAttribute("odr_price", vo.getGds_price());
			

		}else if(type.equals("2")) {
			log.info("장바구니 기반으로 구매" + type);
			// 장바구니 기반으로 사용
			model.addAttribute("cartVOList", cartService.list_cart(mbme_id));
			
			//사용하지 않는 값이지만, 주문하기 클릭을 하게 되면, OrderDetailVO vo2 파라미터에서 에러발생이 되므로, 형식만 유지를 해놨음.
			model.addAttribute("gds_num_pk", 0);
			model.addAttribute("odr_amount", 0);
			model.addAttribute("odr_price", 0);
		}
		
		// 주문입력폼 구성작업
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		
		return FileUploadUtils.getFile(uploadPath, fileName);
	}
	
	// order.jsp에서 type파라미터 정보를 받아와야, 즉시구매, 장바구니를 통한 구매를 분기작업을 할수가 있다.
	// OrderDetailVO vo2 : 장바구니 경유해서 주문하기는 사용이 안된다.
	@PostMapping("/order_buy")
	public String order_buy(OrderVO vo, OrderDetailVO vo2, String type, HttpSession session ) throws Exception{
		
		log.info("order_buy" + vo);
		
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id();
		vo.setMbme_id(mbme_id);
		
		
		if(type.equals("1")) {
			// 장바구니 테이블은 제외. (즉시 구매이므로 장바구니에 상품을 저장안함)
			// 주문, 주문상세테이블만 작업 : gds_num_pk, odr_amount, odr_price
			orderService.orderDirect_buy(vo, vo2); // 즉시구매한 상품구성 해줄것.
		}else if(type.equals("2")) {
			
			// 주문, 주문상세(장바구니 참조) , 장바구니 삭제
			orderService.order_buy(vo, mbme_id);
		}
		
		return "redirect:/";
		
		
	}

}
