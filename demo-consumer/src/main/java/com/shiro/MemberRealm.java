package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bean.Member;
import com.service.MemberService;

public class MemberRealm extends AuthorizingRealm {
	/**
	 * 执行授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("执行授权逻辑");

		// 给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		Subject subject = SecurityUtils.getSubject();
		Member member = (Member) subject.getPrincipal();
		Member m = memberService.getMemberById(member.getId());
		info.addStringPermission(m.getPerms());
		return info;
	}

	@Reference
	private MemberService memberService;

	/**
	 * 执行认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		System.out.println("执行认证逻辑");

		// 编写shiro判断逻辑，判断用户名和密码
		// 1.判断用户名
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

		Member member = memberService.getMemberByName(token.getUsername());
		if (member == null) {
			return null;
		}
		return new SimpleAuthenticationInfo(member, member.getPassword(), "");
	}

}
