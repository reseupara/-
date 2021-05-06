package com.reshome.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reshome.domain.CategoryVO;
import com.reshome.domain.ProductVO;
import com.reshome.dto.Criteria;
import com.reshome.mapper.UserProductMapper;

import lombok.Setter;

@Service
public class UserProductServiceImpl implements UserProductService {
	
	@Setter(onMethod_ = @Autowired)
	private UserProductMapper userProductMapper;

	@Override
	public List<CategoryVO> getCategoryList() throws Exception {
		// TODO Auto-generated method stub
		return userProductMapper.getCategoryList();
	}

	@Override
	public List<CategoryVO> getSubCategoryList(String cg_code) throws Exception {
		// TODO Auto-generated method stub
		return userProductMapper.getSubCategoryList(cg_code);
	}

	@Override
	public List<ProductVO> getProductListBysubCate(Criteria cri, String cg_code) throws Exception {
		// TODO Auto-generated method stub
		return userProductMapper.getProductListBysubCate(cri, cg_code);
	}

	@Override
	public int getTotalCountProductBySubCate(String cg_code) throws Exception {
		// TODO Auto-generated method stub
		return userProductMapper.getTotalCountProductBySubCate(cg_code);
	}

	@Override
	public ProductVO getProductByNum(long gds_num_pk) throws Exception {
		// TODO Auto-generated method stub
		return userProductMapper.getProductByNum(gds_num_pk);
	}

	@Override
	public List<ProductVO> getProductListNew(Criteria cri, Date gds_date_reg) throws Exception {
		// TODO Auto-generated method stub
		return userProductMapper.getProductListNew(cri, gds_date_reg);
	}

}
