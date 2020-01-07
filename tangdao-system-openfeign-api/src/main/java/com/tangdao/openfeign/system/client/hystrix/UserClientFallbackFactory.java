/**
 *
 */
package com.tangdao.openfeign.system.client.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tangdao.openfeign.system.client.UserClient;
import com.tangdao.openfeign.system.model.LoginAuthUser;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 	降級实现
 * @author Ryan Ru(ruyangit@gmail.com)
 */

@Slf4j
@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {

	@Override
	public UserClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new UserClient() {

			@Override
			public LoginAuthUser getLoginAuthUserByUsername(String username) {
				// TODO Auto-generated method stub
				log.error("通过用户名查询用户异常：{}", username, cause);
				return new LoginAuthUser();
			}

			@Override
			public List<String> findAuthoritiesByUserCode(String userCode) {
				// TODO Auto-generated method stub
				log.error("通过用户编码查询用户权限异常：{}", userCode, cause);
				return null;
			}
		};
	}

}
