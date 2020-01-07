package com.tangdao.system.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tangdao.common.collect.MapUtils;
import com.tangdao.common.config.Global;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.openfeign.system.client.UserClient;
import com.tangdao.openfeign.system.model.LoginAuthUser;
import com.tangdao.system.model.domain.Menu;
import com.tangdao.system.model.domain.User;
import com.tangdao.system.service.IMenuService;
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
	
	@Autowired
	private IMenuService  menuService;

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

	@PreAuthorize("#oauth2.hasScope('server') or #username.equals('demo')")
	@GetMapping(value = "/login/{username}")
	public LoginAuthUser getLoginAuthUserByUsername(@PathVariable("username") String username) {
		return userService.getLoginAuthUserByUsername(username);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/access/granted/after")
	public Map<String, Object> getLoginAfter(Principal principal) {
		User user = userService.getByUsername(principal.getName());
		List<Menu> menus = menuService.getMenuByParentCode(user, null);
		Map<String, Object> data = MapUtils.newHashMap();
		data.put("user", user);
		List<Menu> targetMenus = new ArrayList<>();
		List<Menu> sourceMenus = menus.stream()
				.filter(menu -> Global.YES.equals(menu.getIsShow()) && Menu.TYPE_MENU.equals(menu.getMenuType()))
				.collect(Collectors.toList());
		menuService.convertChildList(sourceMenus, targetMenus, Menu.ROOT_CODE);
		data.put("menus", targetMenus);
		return data;
	}
}
