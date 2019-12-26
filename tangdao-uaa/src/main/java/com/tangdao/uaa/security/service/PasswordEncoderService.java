package com.tangdao.uaa.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tangdao.common.utils.PwdUtils;

@Component
public class PasswordEncoderService implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return PwdUtils.encryptPassword(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return PwdUtils.validatePassword(rawPassword.toString(), encodedPassword);
	}
}
