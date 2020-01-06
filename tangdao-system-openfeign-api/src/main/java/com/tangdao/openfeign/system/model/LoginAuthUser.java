/**
 *
 */
package com.tangdao.openfeign.system.model;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */

@Data
@Accessors(chain = true)
public class LoginAuthUser implements UserDetails, CredentialsContainer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7848186533505718243L;

	/**
	 * 正常
	 */
	public static final String STATUS_NORMAL = "0";

	private String userCode;

	private String username;

	private String password;

	private String status;

	private Collection<SimpleGrantedAuthority> authorities;

	@Override
	public void eraseCredentials() {
		this.password = null;
	}

	@Override
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isEnabled();
	}

	@Override
	public boolean isEnabled() {
		if (STATUS_NORMAL.equals(status)) {
			return true;
		}
		return false;
	}

}
