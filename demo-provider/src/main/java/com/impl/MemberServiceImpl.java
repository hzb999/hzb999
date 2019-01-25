package com.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Member;
import com.mapper.MemberMapper;
import com.service.MemberService;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED, timeout = -1)
@com.alibaba.dubbo.config.annotation.Service

public class MemberServiceImpl implements MemberService {

	@Resource
	private MemberMapper mapper;

	@Override
	public Member login(String name, String password) {
		return mapper.login(name, password);
	}

	@Override
	public int addMember(Member member) {
		return mapper.addMember(member);
	}

	@Override
	public int delectMember(Integer id) {
		return mapper.delectMember(id);
	}

	@Override
	public int updateMember(Member member) {
		return mapper.updateMember(member);
	}

	@Override
	public List<Member> getMember() {
		return mapper.getMember();
	}

	@Override
	public Member getMemberById(Integer id) {
		return mapper.getMemberById(id);
	}

	@Override
	@Cacheable(value = "member", key = "'member_' + #p0")
	public Member getMemberByName(String name) {
		return mapper.getMemberByName(name);
	}

}
