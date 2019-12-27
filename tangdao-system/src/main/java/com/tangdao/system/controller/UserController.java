package com.tangdao.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.openfeign.system.client.UserClient;
import com.tangdao.openfeign.system.model.LoginAuthUser;
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
@RequestMapping("/api/users")
public class UserController extends BaseController implements UserClient {

	@Autowired
	private IUserService userService;

	@GetMapping
	public IPage<User> lists(User user, Page<User> page) {
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
		return this.userService.page(page, queryWrapper);
	}

	@GetMapping(value = "/login/{username}")
	public LoginAuthUser getLoginAuthUserByUsername(@PathVariable("username") String username) {
		return userService.getLoginAuthUserByUsername(username);
	}
}
