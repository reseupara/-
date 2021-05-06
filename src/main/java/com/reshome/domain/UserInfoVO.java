package com.reshome.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserInfoVO {
	
	
	private String	user_id;		//아이디
	private String	user_name;		//이름
	private String	user_pw;		//비밀번호
	private String	user_email;		//이메일
	private String	user_zipcode;		//우편번호
	private String	user_add;		//주소
	private String	user_add2;		//주소2
	private String	user_phone;		//휴대폰번호
	private String	user_nick;		//닉네임
	private String	user_eventre;		//수신동의
	private int		user_point;		//포인트
	private Date	user_date_reg;		//가입일
	private Date	user_date_rt;		//수정일
	private Date	user_date_last;		//접속시간
	

}
