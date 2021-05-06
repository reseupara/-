package com.reshome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reshome.domain.MemberVO;
import com.reshome.domain.UserInfoVO;
import com.reshome.dto.LoginDTO;
import com.reshome.mapper.MemberMapper;

import lombok.Setter;

@Service // bean name : memberServiceImpl
public class MemberServiceImpl implements MemberService {
	
	//@Autowired // 스프링에서 제공하는 주입 어노테이션
	//@Inject // 자바 API에서 제공하는 주입 어노테이션.
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

	@Override
	public int checkIdOverlap(String mbme_id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.checkIdOverlap(mbme_id);
	}

	@Override
	public MemberVO login_ok(LoginDTO dto) throws Exception {
		
		return mapper.login_ok(dto);
	}

	@Override
	public MemberVO member_info(String mbme_id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.member_info(mbme_id);
	}

	@Override
	public boolean modifyPOST(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.modifyPOST(vo) == 1;
	}

	@Override
	public void join(MemberVO vo) throws Exception {
		mapper.join(vo);
		
	}

	@Override
	public MemberVO find_pwd(String mbme_id, String mbme_name) throws Exception {
		// TODO Auto-generated method stub
		return mapper.find_pwd(mbme_id, mbme_name);
	}

	@Override
	public String find_id(String mbme_name) throws Exception {
		// TODO Auto-generated method stub
		return mapper.find_id(mbme_name);
	}

	@Override
	public void deleteMb(String mbme_id) throws Exception {
		// TODO Auto-generated method stub
		mapper.deleteMb(mbme_id);
	}

	@Override
	public List<UserInfoVO> userinfo_list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.userinfo_list();
	}

}
