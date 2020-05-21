/*
COPYRIGHT (C) 2017 BY MULDINI. ALL RIGHTS RESERVED.
 */

package com.muldini.mymall.common;

import java.io.Serializable;

/**
 * 用户信息
 * 
 * @author zhong
 * @since 3.1
 */

public final class User implements Serializable {
    
    private static final long serialVersionUID = -4667734894121828162L;
    
    private String name; // 用户id
    private String password; // 用户密码
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}

   
}
