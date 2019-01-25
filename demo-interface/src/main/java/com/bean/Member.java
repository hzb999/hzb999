package com.bean;

import java.io.Serializable;

public class Member implements Serializable {
	private Integer id;
	private String name;
	private String password;
	private String perms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Member(Integer id, String name, String password, String perms) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.perms = perms;
	}

	public Member() {
	}

}
