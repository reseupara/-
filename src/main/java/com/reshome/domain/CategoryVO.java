package com.reshome.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CategoryVO {
	
	private String cg_code;		//현재 카테고리
	private String cg_code_up;	//상우 카테고리
	private String cg_name;		//카테고리 이름

}
