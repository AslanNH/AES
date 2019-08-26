package com.nh.shiro.factory;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String,String>buildFilterChainDefinitionMap(){
		LinkedHashMap <String,String> map = new LinkedHashMap<>();
		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/user.jsp", "roles[user]");
		map.put("/admin.jsp", "roles[admin]");
		map.put("/**", "authc");
		
		return map;
	}
}
