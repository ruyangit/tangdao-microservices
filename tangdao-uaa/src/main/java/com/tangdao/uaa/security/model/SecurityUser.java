package com.tangdao.uaa.security.model;

import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: SecurityUser.java
 * @Description: TODO(用户登录信息)
 * @author ruyang
 * @date 2018年11月1日 下午3:25:22
 * 
 */
@Data
@Accessors(chain = true)
public class SecurityUser implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private Boolean enabled;
	private String password;
	private Collection<SimpleGrantedAuthority> authorities;

	@Override
	public Collection<SimpleGrantedAuthority> getAuthorities() {
//        if (authorities == null) {
//            this.authorities = this.getRoles().stream().map(
//            		role -> 
//            		new SimpleGrantedAuthority(role)
//            		).collect(Collectors.toList());
//        }
		return authorities;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void eraseCredentials() {
		this.password = null;
	}
}
