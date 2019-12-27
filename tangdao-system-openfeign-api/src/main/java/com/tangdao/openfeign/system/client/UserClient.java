/**
 *
 */
package com.tangdao.openfeign.system.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tangdao.openfeign.system.client.hystrix.UserClientFallbackFactory;
import com.tangdao.openfeign.system.common.constants.ServiceNameConstants;
import com.tangdao.openfeign.system.model.LoginAuthUser;

/**
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */
//@FeignClient(value = ServiceNameConstants.SYSTEM_SERVICE, configuration = OAuth2FeignAutoConfiguration.class, fallbackFactory = UserClientFallbackFactory.class, decode404 = true)
@FeignClient(value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = UserClientFallbackFactory.class, decode404 = true)
public interface UserClient extends ServiceNameConstants {

	@RequestMapping(value = SYSTEM_CONTEXT_PATH + "/api/users/login/{username}", method = RequestMethod.GET)
	public LoginAuthUser getLoginAuthUserByUsername(@PathVariable("username") String username);
}
