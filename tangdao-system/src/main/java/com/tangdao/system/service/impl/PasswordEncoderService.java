package com.tangdao.system.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tangdao.common.codec.EncodeUtils;
import com.tangdao.common.codec.Sha1Utils;

@Component
public class PasswordEncoderService implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return encryptPassword(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return validatePassword(rawPassword.toString(), encodedPassword);
	}
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	/**
	 * 	生成密文密码，生成随机的16位salt并经过1024次 sha-1 hash
	 * @param plainPassword 明文密码
	 * @return 16位salt密钥  + 40位hash密码
	 */
	public String encryptPassword(String plainPassword) {
		String plain = EncodeUtils.decodeHtml(plainPassword);
		byte[] salt = Sha1Utils.genSalt(SALT_SIZE);
		byte[] hashPassword = Sha1Utils.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword);
	}
	
	/**
	 * 	验证密码正确性
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public boolean validatePassword(String plainPassword, String password) {
		try{
			String plain = EncodeUtils.decodeHtml(plainPassword);
			byte[] salt = EncodeUtils.decodeHex(password.substring(0, 16));
			byte[] hashPassword = Sha1Utils.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
			return password.equals(EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword));
		}catch(Exception e){
			return false;
		}
	}

}
