package com.reshome.service;

import java.util.List;

import com.reshome.domain.CategoryVO;
import com.reshome.domain.ProductVO;
import com.reshome.dto.Criteria;

public interface AdProductService {
	
	public List<CategoryVO> getCategoryList() throws Exception;
	
	public List<CategoryVO> getSubCategoryList(String cg_code) throws Exception;
	
	public void product_insert(ProductVO vo) throws Exception;
	
	public List<ProductVO> product_list(Criteria cri) throws Exception;
	
	public int getTotalCountProduct(Criteria cri) throws Exception;
	
	public ProductVO product_modify(Long gds_num_pk) throws Exception;
	
	public void product_modify(ProductVO vo) throws Exception;
	
	public void product_delete(long gds_num_pk) throws Exception;
	
	

}
