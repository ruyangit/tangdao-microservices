package com.tangdao.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.openfeign.system.client.UserClient;
import com.tangdao.openfeign.system.model.LoginAuthUser;
import com.tangdao.system.model.domain.Menu;
import com.tangdao.system.model.domain.Role;
import com.tangdao.system.model.domain.User;
import com.tangdao.system.service.IMenuService;
import com.tangdao.system.service.IRoleService;
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
	private IRoleService roleService;

	@Autowired
	private IMenuService menuService;

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
	@GetMapping(value = "/login")
	public LoginAuthUser getLoginAuthUserByUsername(@RequestParam("username") String username) {
		return userService.getLoginAuthUserByUsername(username);
	}

//	@PreAuthorize("isAuthenticated()")
//	@GetMapping(value = "/access/granted/after")
//	public Map<String, Object> accessGrantedAfter(Principal principal) {
//		// 用户信息
//		User user = userService.getByUsername(principal.getName());
//		List<Menu> menus = menuService.getMenuByParentCode(user, null);
//		Map<String, Object> data = MapUtils.newHashMap();
//		data.put("user", user);
//		List<Menu> targetMenus = new ArrayList<>();
//		List<Menu> sourceMenus = menus.stream()
//				.filter(menu -> Global.YES.equals(menu.getIsShow()) && Menu.TYPE_MENU.equals(menu.getMenuType()))
//				.collect(Collectors.toList());
//		menuService.convertChildList(sourceMenus, targetMenus, Menu.ROOT_CODE);
//		data.put("menus", targetMenus);
//		return data;
//	}

	@Override
	@PreAuthorize("#oauth2.hasScope('server')")
	@GetMapping(value = "/authorities")
	public List<String> findAuthoritiesByUserCode(@RequestParam("user_code") String userCode) {
		User user = userService.getByUserCode(userCode);
		// 角色授权
		List<Role> roles = roleService.findByUserCode(user.getUserCode());
		Set<String> collect = roles.stream()
				.map(r -> "ROLE_" + r.getRoleCode().toUpperCase())
				.collect(Collectors.toSet());
		collect.add("ROLE_USER");
//		//菜单授权
		List<Menu> menus = menuService.getMenuByParentCode(user, null);
		menus.stream().filter(m -> StringUtils.isNotBlank(m.getPermission())).forEach(m -> {
			// 添加基于Permission的权限信息
			for (String permission : StringUtils.split(m.getPermission(), ",")) {
				collect.add(permission);
			}
		});
		return new ArrayList<String>(collect);
	}

}
