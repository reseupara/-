package com.reshome.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reshome.domain.MemberVO;
import com.reshome.domain.ReviewVO;
import com.reshome.dto.Criteria;
import com.reshome.dto.ReviewPageDTO;
import com.reshome.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/review/*")
@AllArgsConstructor
public class ReviewController {
	
	private ReviewService reviewService; // @AllArgsConstructor : 모든 필드에 생성자메서드가 생성이되고, 생성자는 어노테이션을 생략하고, 자동으로 주입이 이루어진다.
	// @Setter(onMethod_ = @Autowired) 을 안썼다면 주입을 생성자로 받은 것
	// 생성자 메서드를 주입받을 때 어노테이션 생략가능 
	
	
	// 상품후기목록(페이징기능포함)
	@GetMapping(value = "/pages/{gds_num_pk}/{page}",
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReviewPageDTO> getReviewListPage(@PathVariable("gds_num_pk") int gds_num_pk, @PathVariable("page") int page) {
		
		ResponseEntity<ReviewPageDTO> entity = null;
		
		Criteria cri = new Criteria(page, 5);
		
		log.info("상품번호 : " + gds_num_pk);
		log.info("cri" + cri);
		
		// 상품후기 개수(페이징 때문에 필요)
		// 상품후기 목록
		
		try {
			entity = new ResponseEntity<ReviewPageDTO>(reviewService.getReviewListWithPaging(cri, gds_num_pk), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<ReviewPageDTO>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@PostMapping(value = "/review_register")
	@ResponseBody
	public ResponseEntity<String> review_register(ReviewVO vo, HttpSession session) throws Exception{
		
		String mbme_id = ((MemberVO) session.getAttribute("loginStatus")).getMbme_id(); // 사용자 아이디를 확보
		vo.setMbme_id(mbme_id);	//vo에 집어넣음
		log.info("review_register: " + vo);
		
		ResponseEntity<String> entity = null;
		
		try {
			reviewService.review_register(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	// 상품후기 수정하기
	@PostMapping(value = "/review_modify")
	@ResponseBody
	public ResponseEntity<String> review_modify(ReviewVO vo) throws Exception{
		
		log.info("review_register: " + vo);
		
		ResponseEntity<String> entity = null;
		
		try {
			reviewService.review_modify(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	// 상품후기 삭제하기
	@PostMapping(value = "/review_delete")
	@ResponseBody
	public ResponseEntity<String> review_delete(int rv_num_pk) throws Exception{
		
		log.info("review_register: " + rv_num_pk);
		
		ResponseEntity<String> entity = null;
		
		try {
			reviewService.review_delete(rv_num_pk);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	

}
