package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alibaba.dubbo.config.support.Parameter;
import com.bean.Member;

@Mapper
public interface MemberMapper {

	// 登录
	@Select("select * from member where name=#{name} and password=#{password}")
	Member login(@Param("name")String name,@Param("password")String password);

	// 增加
	int addMember(Member member);

	// 删除
	@Delete("delete from member where id=#{id}")
	int delectMember(@Param("id") Integer id);

	// 修改
	@Update("update member set name=#{name},password=#{password},perms=#{perms} where id=#{id}")
	int updateMember(Member member);

	// 获得所有member
	@Select("select *from member")
	List<Member> getMember();

	// 根据id获得member
	@Select("select * from member where id=#{id}")
	Member getMemberById(@Param("id") Integer id);

	//根据name查询
	@Select("select * from member where name=#{name}")
	Member getMemberByName(String name);
}
