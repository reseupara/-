package com.reshome.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reshome.domain.CategoryVO;
import com.reshome.domain.ProductVO;
import com.reshome.dto.Criteria;
import com.reshome.service.UserProductService;
import com.reshome.util.FileUploadUtils;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/product/*")
public class UserProductController {
	
	@Setter(onMethod_ = @Autowired)
	private UserProductService userProductService;
	
	@Resource(name="uploadPath") // servlet-context에서 설정한 업로드 bean 이름을 가져온 것. 아래 이름과 상관없다. 달라도 됨.
	private String uploadPath;
	
	@ResponseBody
	@GetMapping("/subCategoryList/{cg_code}")
	public ResponseEntity<List<CategoryVO>> subCategoryList(@PathVariable("cg_code") String cg_code) throws Exception{
		
		log.info("subCategoryList: " + cg_code);
		
		ResponseEntity<List<CategoryVO>> entity = null;
		
		try {
			entity = new ResponseEntity<List<CategoryVO>>(userProductService.getSubCategoryList(cg_code), HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		
		
		return entity;
	}
	
	// 2차카테고리에 의한 상품목록정보
	@GetMapping("/product_list")
	public String ProductListBysubCate(@ModelAttribute("cri") Criteria cri, String cg_code, Model model) throws Exception{
		
		log.info("productListBysubCate: " + cg_code);
		
		log.info("Criteria: " + cri);
		
		cri.setAmount(9); // 한 페이지에 뿌리고자 하는 상품출력 개수설정.
		
		model.addAttribute("productVOList", userProductService.getProductListBysubCate(cri, cg_code));
		
		return "/product/product_list";
	}
	
	
	// 새로 등록된 상품 순으로 보여주는
	@GetMapping("/main2")
	public String ProductListNew(@ModelAttribute("cri") Criteria cri, Date gds_date_reg, Model model) throws Exception{
		
		log.info("productListBysubCate: " + gds_date_reg);
		
		log.info("Criteria: " + cri);
		
		cri.setAmount(8); // 한 페이지에 뿌리고자 하는 상품출력 개수설정.
		
		model.addAttribute("newProductVOList", userProductService.getProductListNew(cri, gds_date_reg));
		
		return "/main2";
	}
	
	
	
	
	// 상품이미지 뷰
	@ResponseBody
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		
		
		return FileUploadUtils.getFile(uploadPath, fileName);
	}
	
	// 상품상세설명(상품조회)
	@GetMapping("/product_read")
	public void product_read(@RequestParam("gds_num_pk") long gds_num_pk, Model model) throws Exception{
		
		log.info("product_read: " + gds_num_pk);
		
		ProductVO vo = userProductService.getProductByNum(gds_num_pk);
		vo.setGds_img(FileUploadUtils.thumbToOriginName(vo.getGds_img()));
		// 기본이미지를 설정작업
		
		model.addAttribute("productVO", vo);
		
	}
	
	
}
