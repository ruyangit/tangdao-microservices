/**
 *
 */
package com.tangdao.openfeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tangdao.openfeign.client.hystrix.UserClientFallbackFactory;
import com.tangdao.openfeign.common.constants.ServiceNameConstants;
import com.tangdao.openfeign.model.UserVo;

/**
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */
//@FeignClient(value = ServiceNameConstants.SYSTEM_SERVICE , configuration = OAuth2FeignAutoConfiguration.class, fallback = EmailFeignHystrix.class)
@FeignClient(value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = UserClientFallbackFactory.class, decode404 = true)
public interface UserClient {

	@GetMapping(value = "/system/users/{username}")
	public UserVo getUserByUsername(@PathVariable(name = "username") String username);
}
