package com.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置类
 * 
 * @author My
 *
 */
@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 添加Shiro内置过滤器
		/**
		 * Shiro内置过滤器，可以实现权限相关的拦截器 常用的过滤器： anon: 无需认证（登录）可以访问 authc: 必须认证才可以访问 user:
		 * 如果使用rememberMe功能可以直接访问 perms: 该资源必须得到资源权限才可以访问 role: 该资源必须得到角色权限才可以访问
		 */
		Map<String, String> filterMap = new LinkedHashMap<String, String>();
		filterMap.put("/login", "anon");
		// 授权拦截
		filterMap.put("/add", "perms[member:one]");
		filterMap.put("/*", "authc");

		// 登录错误跳转
		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		// 未授权跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		return shiroFilterFactoryBean;

	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("memberRealm") MemberRealm memberRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 关联realm
		securityManager.setRealm(memberRealm);
		return securityManager;
	}

	@Bean(name = "memberRealm")
	public MemberRealm getRealm() {
		return new MemberRealm();
	}
}
