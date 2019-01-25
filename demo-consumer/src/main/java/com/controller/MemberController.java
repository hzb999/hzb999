package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bean.Member;
import com.service.MemberService;

@Controller
public class MemberController {
	@Reference
	private MemberService memberService;

	@RequestMapping("/login")
	public String login(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			@RequestParam(name = "password", required = false, defaultValue = "") String password, Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		try {
			subject.login(token);
			return "/test";
		} catch (UnknownAccountException e) {
			model.addAttribute("msg", "用户名不存在");
			return "login";

		} catch (IncorrectCredentialsException e) {
			model.addAttribute("msg", "密码错误");
			return "login";
		}
	}

	@RequestMapping("/test")
	public String test() {
		return "test";
	}

	@RequestMapping("/add")
	public String add() {
		return "member/add";
	}

	@RequestMapping("/doAdd")
	public String doAdd(Member m, HttpServletRequest request) {
		memberService.addMember(m);
		return "redirect:/toMemberList";
	}

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "/login";
	}

	@RequestMapping("/unAuth")
	public String unAuth() {
		return "unAuth";

	}

	@RequestMapping("/toMemberList")
	public ModelAndView memberList(ModelAndView model) {
		List<Member> list = memberService.getMember();
		model.addObject("list", list);
		model.setViewName("member/memberList");
		return model;
	}

	@RequestMapping("/doDelete")
	public String doDelete(@RequestParam(name = "id") Integer id) {
		memberService.delectMember(id);
		return "redirect:/toMemberList";
	}

	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(@RequestParam(name = "id") Integer id, ModelAndView model) {
		model.addObject("id", id);
		model.setViewName("member/update");
		return model;
	}

	@RequestMapping("doUpdate")
	public String doUpdate(Member member, HttpServletRequest request) {
		memberService.updateMember(member);
		System.out.println(member);
		return "redirect:/toMemberList";
	}
}
