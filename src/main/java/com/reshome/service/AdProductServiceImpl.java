package com.reshome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reshome.domain.CategoryVO;
import com.reshome.domain.ProductVO;
import com.reshome.dto.Criteria;
import com.reshome.mapper.AdProductMapper;

import lombok.Setter;

@Service
public class AdProductServiceImpl implements AdProductService {

	@Setter(onMethod_ = @Autowired)
	private AdProductMapper pro_mapper;
	
	@Override
	public List<CategoryVO> getCategoryList() throws Exception {
		// TODO Auto-generated method stub
		return pro_mapper.getCategoryList();
	}

	@Override
	public List<CategoryVO> getSubCategoryList(String cg_code) throws Exception {
		// TODO Auto-generated method stub
		return pro_mapper.getSubCategoryList(cg_code);
	}

	@Override
	public void product_insert(ProductVO vo) throws Exception {
		// TODO Auto-generated method stub
		pro_mapper.product_insert(vo);
	}

	@Override
	public List<ProductVO> product_list(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return pro_mapper.product_list(cri);
	}

	@Override
	public int getTotalCountProduct(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return pro_mapper.getTotalCountProduct(cri);
	}

	@Override
	public ProductVO product_modify(Long gds_num_pk) throws Exception {
		// TODO Auto-generated method stub
		return pro_mapper.product_modify(gds_num_pk);
	}

	@Override
	public void product_modify(ProductVO vo) throws Exception {
		// TODO Auto-generated method stub
		pro_mapper.product_modifyOK(vo);
	}

	@Override
	public void product_delete(long gds_num_pk) throws Exception {
		// TODO Auto-generated method stub
		pro_mapper.product_deleteOk(gds_num_pk);
		
	}

}
