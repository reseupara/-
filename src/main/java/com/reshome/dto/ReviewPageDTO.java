package com.reshome.dto;

import java.util.List;

import com.reshome.domain.ReviewVO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor	// 두개의 필드에 대한 생성자 메서드가 생성되어있다.
public class ReviewPageDTO {
	
	private int reviewCnt;		// 상품후기 개수
	private List<ReviewVO> list;	// 상품후기 목록

}
