package com.reshome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.reshome.domain.ReviewVO;
import com.reshome.dto.Criteria;

public interface ReviewMapper {
																				// 이거 상품번호? 비교해볼것
	public List<ReviewVO> getReviewListWithPaging(@Param("cri") Criteria cri, @Param("gds_num_pk") int gds_num_pk) throws Exception;
	
	public int getCountByProduct_gds_num_pk(long gds_num_pk);
	
	public void review_register(ReviewVO vo) throws Exception;
	
	public void review_modify(ReviewVO vo) throws Exception;
	
	public void review_delete(int rv_num_pk) throws Exception;

}
