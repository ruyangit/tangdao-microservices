package com.tangdao.uaa.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tangdao.openfeign.system.client.UserClient;
import com.tangdao.openfeign.system.model.LoginAuthUser;

/**
 * @ClassName: UserDetailsServiceImpl.java
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ruyang
 * @date 2018年10月16日 上午11:08:29
 * 
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginAuthUser loginAuthUser = this.userClient.getLoginAuthUserByUsername(username);
		if (loginAuthUser == null) {
			throw new UsernameNotFoundException("用户名不存在！");
		}
		if(!loginAuthUser.isEnabled()) {
			 throw new DisabledException("用户已被禁用");
		}
		loginAuthUser.setAuthorities(this.userClient.findAuthoritiesByUserCode(loginAuthUser.getUserCode()));
		return loginAuthUser;
	}

}
