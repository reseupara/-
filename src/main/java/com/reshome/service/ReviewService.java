package com.reshome.service;

import com.reshome.domain.ReviewVO;
import com.reshome.dto.Criteria;
import com.reshome.dto.ReviewPageDTO;

public interface ReviewService {
	
	public ReviewPageDTO getReviewListWithPaging(Criteria cri, int gds_num_pk) throws Exception;
	
	public void review_register(ReviewVO vo) throws Exception;
	
	public void review_modify(ReviewVO vo) throws Exception;
	
	public void review_delete(int rv_num_pk) throws Exception;
	

}
