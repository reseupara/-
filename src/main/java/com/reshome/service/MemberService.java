package com.reshome.service;

import java.util.List;

import com.reshome.domain.MemberVO;
import com.reshome.domain.UserInfoVO;
import com.reshome.dto.LoginDTO;

public interface MemberService {
	
	// 아이디 중복체크
	public int checkIdOverlap(String mbme_id) throws Exception;
	
	// 회원가입 저장
	public void join(MemberVO vo) throws Exception;
	
	// 로그인
	public MemberVO login_ok(LoginDTO dto) throws Exception;
	
	// 회원수정 폼
	public MemberVO member_info(String mbme_id) throws Exception;
	
	// 회원수정 저장
	public boolean modifyPOST(MemberVO vo) throws Exception;
	
	// 아이디 찾기 : 화면출력
	public String find_id(String mbme_name) throws Exception;
	
	// 비밀번호 찾기 // 파라미터가 두개 이상 들어오면 @Param() 필수.
	public MemberVO find_pwd(String mbme_id, String mbme_name) throws Exception;

	// 회원삭제
	public void deleteMb(String mbme_id) throws Exception;
	
	public List<UserInfoVO> userinfo_list() throws Exception;
	

}
