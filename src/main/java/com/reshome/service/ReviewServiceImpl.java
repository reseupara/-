package com.reshome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reshome.domain.ReviewVO;
import com.reshome.dto.Criteria;
import com.reshome.dto.ReviewPageDTO;
import com.reshome.mapper.ReviewMapper;

import lombok.Setter;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Setter(onMethod_ = @Autowired)
	private ReviewMapper reviewMapper;

	@Override	// 이 안에 두개 있다. 상품후기 개수, 상품후기 목록. 이걸 컨트롤러단에서 써야한다.
	public ReviewPageDTO getReviewListWithPaging(Criteria cri, int gds_num_pk) throws Exception {
		// TODO Auto-generated method stub
		return new ReviewPageDTO(reviewMapper.getCountByProduct_gds_num_pk(gds_num_pk), reviewMapper.getReviewListWithPaging(cri, gds_num_pk));
	}

	@Override
	public void review_register(ReviewVO vo) throws Exception {
		// TODO Auto-generated method stub
		reviewMapper.review_register(vo);
		
	}

	@Override
	public void review_modify(ReviewVO vo) throws Exception {
		// TODO Auto-generated method stub
		reviewMapper.review_modify(vo);
		
	}

	@Override
	public void review_delete(int rv_num_pk) throws Exception {
		// TODO Auto-generated method stub
		reviewMapper.review_delete(rv_num_pk);
		
	}

}
