package com.shijie99.wcf.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm2 extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
//		String userName = (String)getAvailablePrincipal(principals);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> s = new HashSet<String>();
		s.add("p1");
		s.add("p2");
		info.setStringPermissions(s);
		Set<String> r= new HashSet<String>();
		r.add("r1");
		r.add("r2");
		info.setRoles(r);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		String username = upToken.getUsername();
		String password = String.valueOf(upToken.getPassword());
		if(username.equals("javass")){
			throw new AuthenticationException("MyRealm2»œ÷§ ß∞‹");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
		return info;
	}

}
