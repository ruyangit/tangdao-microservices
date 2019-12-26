package com.tangdao.system.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.openfeign.client.UserClient;
import com.tangdao.openfeign.model.UserVo;
import com.tangdao.system.model.domain.User;
import com.tangdao.system.service.IUserService;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@RestController
public class UserController implements UserClient{

	@Autowired
	private IUserService userService;

	@GetMapping("/system/users")
	public @ResponseBody IPage<User> lists(User user, HttpServletRequest request) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		if (StringUtils.isNotBlank(user.getStatus())) {
			queryWrapper.eq("status", user.getStatus());
		}
		if (StringUtils.isNotBlank(user.getUserType())) {
			queryWrapper.eq("user_type", user.getUserType());
		}
		if (StringUtils.isNotBlank(user.getUsername()) || StringUtils.isNotBlank(user.getNickname())) {
			queryWrapper.like("username", user.getUsername()).or().like("nickname", user.getNickname());
		}
		return this.userService.page(user.getPage(), queryWrapper);
	}

	@Override
	public UserVo getUserByUsername(String username) {
		User user = userService.getUserByUsername(username);
		if(user==null) {
			return null;
		}
		UserVo userVo = new UserVo();
		userVo.setUserCode(user.getUserCode());
		userVo.setUsername(user.getUsername());
		userVo.setPassword(user.getPassword());
		userVo.setStatus(user.getStatus());
		return userVo;
	}

}
