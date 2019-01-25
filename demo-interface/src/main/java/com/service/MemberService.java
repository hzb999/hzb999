package com.service;

import java.util.List;

import com.bean.Member;

public interface MemberService {

	// 登录
	Member login(String name,String password);

	// 增加
	int addMember(Member member);

	// 删除
	int delectMember(Integer id);

	// 修改
	int updateMember(Member member);

	// 获得所有member
	List<Member> getMember();
	
	//根据id获得member
	Member getMemberById(Integer id);
	
	//根据name查询
	Member getMemberByName(String name);

}
