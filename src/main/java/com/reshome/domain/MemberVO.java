package com.reshome.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	
	private String	mbme_id;		//아이디
	private String	mbme_name;		//이름
	private String	mbme_pw;		//비밀번호
	private String	mbme_email;		//이메일
	private String	mbme_zipcode;		//우편번호
	private String	mbme_add;		//주소
	private String	mbme_add2;		//주소2
	private String	mbme_phone;		//휴대폰번호
	private String	mbme_nick;		//닉네임
	private String	mbme_eventre;		//수신동의
	private int		mbme_point;		//포인트
	private Date	mbme_date_reg;		//가입일
	private Date	mbme_date_rt;		//수정일
	private Date	mbme_date_last;		//접속시간
	

}
