package com.shijie99.wcf.shiro;

import java.util.Iterator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class HelloWord {
	
	public static void main(String[] args) {
		Ini ini = new Ini();
		ini.loadFromPath("classpath:shiro.ini");
		Iterator<String> it = ini.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Section s = ini.get(key);
			System.out.println(key+"==="+s);
			Iterator<String> its = s.keySet().iterator();
			while(its.hasNext()){
				String keys = its.next();
				System.out.println(keys+":"+s.get(keys));
			}
		}
//		Realm r= new IniRealm();
//		DefaultSecurityManager s = new DefaultSecurityManager(r);
		Factory<SecurityManager> f = new IniSecurityManagerFactory(ini);
		SecurityManager s = f.getInstance();
		SecurityUtils.setSecurityManager(s);
		UsernamePasswordToken token = new UsernamePasswordToken("javass", "ccs");
		token.setRememberMe(true);
		Subject user = SecurityUtils.getSubject();
		user.login(token);
		boolean flag = user.isPermitted("p1");
		System.out.println("flag=="+flag);
	}
}
