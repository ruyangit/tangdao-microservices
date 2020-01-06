package com.tangdao.uaa.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tangdao.common.constant.DefaultConstant;
import com.tangdao.openfeign.system.client.UserClient;
import com.tangdao.openfeign.system.model.LoginAuthUser;
import com.tangdao.uaa.security.model.SecurityUser;

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
		LoginAuthUser user = userClient.getLoginAuthUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在！");
		}
		SecurityUser securityUser = new SecurityUser();
		securityUser.setId(user.getUserCode());
		securityUser.setUsername(user.getUsername());
		securityUser.setPassword(user.getPassword());
		securityUser.setEnabled(user.isEnabled());
		return securityUser;
	}

}
