package com.shijie99.wcf.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

public class MyAuthenticationStrategy extends AbstractAuthenticationStrategy {
	@Override
	public AuthenticationInfo afterAttempt(Realm realm,
			AuthenticationToken token, AuthenticationInfo singleRealmInfo,
			AuthenticationInfo aggregateInfo, Throwable t)
			throws AuthenticationException {
		if(realm.getName().equals("myRealm2")){
			if(singleRealmInfo==null||singleRealmInfo.getPrincipals()==null){
				throw new AuthenticationException("主站认证未通过");
			}
		}
		return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo,
				t);
	}
}
