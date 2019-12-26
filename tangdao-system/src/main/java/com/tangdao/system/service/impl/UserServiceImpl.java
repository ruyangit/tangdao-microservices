package com.tangdao.system.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tangdao.common.config.Global;
import org.tangdao.modules.sys.utils.UserUtils;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.common.service.impl.CrudServiceImpl;
import com.tangdao.system.mapper.UserMapper;
import com.tangdao.system.model.domain.User;
import com.tangdao.system.service.IUserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Service
public class UserServiceImpl extends CrudServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {
	
	@Autowired
	private PasswordEncoderService passwordEncoderService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = UserUtils.getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("账号不存在");
		}
		return user;
	}

	public boolean checkUsernameExists(String oldUsername, String username) {
		if (username != null && username.equals(oldUsername)) {
			return true;
		} else if (username != null && this.count(Wrappers.<User>lambdaQuery().eq(User::getUsername, username)) == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void updateUserinfo(User user) {
		if(user == null) {
			return;
		}
		User update = new User();
		update.setUserCode(user.getUserCode());
		update.setUsername(user.getUsername());
		update.setEmail(user.getEmail());
		update.setMobile(user.getMobile());
		update.setPhone(user.getPhone());
		update.setSex(user.getSex());
		update.setAvatar(user.getAvatar());
		update.setSign(user.getSign());
		this.baseMapper.updateById(update);
	}

	@Override
	public void updateLoginUserinfo(User user) {
		if(user == null) {
			return;
		}
		User update = new User();
		update.setUserCode(user.getUserCode());
		update.setLastLoginDate(new Date());
		update.setLastLoginIp(user.getLastLoginIp());
		this.baseMapper.updateById(update);
	}

	@Override
	public void updatePassword(String userCode, String password) {
		if(StringUtils.isEmpty(userCode)) {
			return;
		}
		User user = new User();
		user.setUserCode(userCode);
		if(StringUtils.isEmpty(password)) {
			password = Global.getConfig("sys.user.initPassword");
		}
		user.setPassword(passwordEncoderService.encryptPassword(password));
		this.baseMapper.updateById(user);
	}
	
	@Override
	public void updateMgrType(User user) {
		if(user == null) {
			return;
		}
		User update = new User();
		update.setUserCode(user.getUserCode());
		update.setMgrType(user.getMgrType());
		this.baseMapper.updateById(update);
	}

	@Override
	public User getUserByUsername(String username) {
		return this.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
	}
	
	@Override
	public User getByUserCode(String userCode) {
		return this.getOne(Wrappers.<User>lambdaQuery().eq(User::getUserCode, userCode));
	}
	
	@Override
	public boolean saveOrUpdate(User user) {
		if(user.getIsNewRecord()) {
			String password = user.getPassword();
			if(StringUtils.isEmpty(password)) {
				password = Global.getConfig("sys.user.initPassword");
			}
			user.setPassword(passwordEncoderService.encryptPassword(password));
			if(StringUtils.isEmpty(user.getUserType())) {
				user.setUserType(User.USER_TYPE_NONE);
			}
			if(StringUtils.isEmpty(user.getMgrType())) {
				user.setMgrType(User.MGR_TYPE_NOT_ADMIN);
			}
		}
		return super.saveOrUpdate(user);
	}
	
	@Override
	public void saveAuth(User user) {
		if(StringUtils.isEmpty(user.getUserCode())) {
			return;
		}
		this.baseMapper.deleteUserRole(user.getUserCode());
		if(StringUtils.isNotBlank(user.getRoleCodes())) {
			this.baseMapper.insertUserRole(user.getUserCode(), user.getRoleCodes().split(","));
		}
	}
}
