package com.reshome.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.reshome.domain.CategoryVO;
import com.reshome.domain.ProductVO;
import com.reshome.dto.Criteria;

public interface UserProductService {
	
	// 1차 카테고리
	public List<CategoryVO> getCategoryList() throws Exception;
	// 2차 카테고리
	public List<CategoryVO> getSubCategoryList(String cg_code) throws Exception;
	
	// 2차 카테고리에 해당하는 상품목록
	public List<ProductVO> getProductListBysubCate(@Param("cri") Criteria cri,@Param("cg_code") String cg_code) throws Exception;
	
	public int getTotalCountProductBySubCate(String cg_code) throws Exception;

	public ProductVO getProductByNum(long gds_num_pk) throws Exception;
	
	public List<ProductVO> getProductListNew(@Param("cri") Criteria cri,@Param("gds_date_reg") Date gds_date_reg) throws Exception;
	
}
