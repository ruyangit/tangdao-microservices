/**
 *
 */
package com.tangdao.system.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */
public class UserUtils {

	/**
	 * 获取当前登录用户对象
	 * 
	 * @return
	 */
	public static String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
//		if (!(auth instanceof AnonymousAuthenticationToken)) {
		Object principal = authentication.getPrincipal();
		if (principal != null) {
			System.out.println("principal:"+principal);
			return String.valueOf(principal);
		}
		return null;
	}
}
