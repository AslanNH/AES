package com.nh.shiro.hanldes;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nh.shiro.service.ShiroService;

@RequestMapping("/shiro")
@Controller
public class ShiroHandler {

	@Autowired
	private ShiroService shiroService;
	
	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(){ 
		shiroService.testMethod();
		return"redirect:/list.jsp";
	}
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,@RequestParam("password") String password){
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				username, password);
		token.setRememberMe(true);
		try {
			//执行登陆
			currentUser.login(token);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/list.jsp";
	}
	@RequestMapping("/logout")
	public String login(){
		Subject currentUser = SecurityUtils.getSubject();
		
		try {
			//执行登陆
			currentUser.logout();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "login";
	}
}
