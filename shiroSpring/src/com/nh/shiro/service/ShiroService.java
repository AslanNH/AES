package com.nh.shiro.service;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresRoles;

public class ShiroService {

	@RequiresRoles({"admin"})
	public void testMethod(){
		System.out.println("testMethod"+new Date());
	}
}
