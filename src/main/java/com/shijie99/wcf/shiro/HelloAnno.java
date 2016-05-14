package com.shijie99.wcf.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class HelloAnno {
	
	private static Object s = new Object();
	@Autowired
	private SecurityManager securityManager;
	
	
	@RequiresAuthentication
//	@RequiresGuest
	@RequiresPermissions({"p3"})
	public void t(){
		System.out.println("ok================");
	}
	
	public void setManager(){
		SecurityUtils.setSecurityManager(securityManager);
	}
	
	public void login(){
		UsernamePasswordToken token = new UsernamePasswordToken("javass", "ccs");
		token.setRememberMe(true);
		
		Subject user = SecurityUtils.getSubject();
		user.login(token);
	}
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		HelloAnno t= (HelloAnno)ctx.getBean("helloAnno");
		t.setManager();
		try{
			t.login();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		t.t();
		
		try {
			synchronized (s) {
				s.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
