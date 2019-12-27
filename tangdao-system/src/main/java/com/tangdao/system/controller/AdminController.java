package com.tangdao.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tangdao.common.lang.StringUtils;
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
@RequestMapping("/api/admins")
public class AdminController {

	@Autowired
	private IUserService userService;

	@GetMapping
	public IPage<User> lists(User user, Page<User> page) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		if (StringUtils.isNotBlank(user.getStatus())) {
			queryWrapper.eq("status", user.getStatus());
		}
		if (StringUtils.isNotBlank(user.getUsername())) {
			queryWrapper.like("username", user.getUsername());
		}
		queryWrapper.eq("mgr_type", User.MGR_TYPE_DEFAULT_ADMIN);
		return this.userService.page(page, queryWrapper);
	}

	@PostMapping
	public boolean create(@Validated @RequestBody User user) {
		return true;
//		if (!UserUtils.getUser().isSuperAdmin()){
//			return renderResult(Global.FALSE, "越权操作，只有超级管理员才能修改此数据！");
//		}
//		if (User.isSuperAdmin(user.getUserCode())) {
//			return renderResult(Global.FALSE, "非法操作，不能够操作此用户！");
//		}
//		if (!User.USER_TYPE_EMPLOYEE.equals(user.getUserType())){
//			return renderResult(Global.FALSE, "非法操作，不能够操作此用户！");
//		}
//		if (!userService.checkUsernameExists(oldUsername, user.getUsername())) {
//			return renderResult(Global.FALSE, "保存用户'" + user.getUsername() + "'失败，登录账号已存在");
//		}
//		if (user.getIsNewRecord()){
//			user.setUserType(User.USER_TYPE_NONE); // 仅登录用户
//		}
//		user.setMgrType(User.MGR_TYPE_DEFAULT_ADMIN); // 租户管理员
//		userService.saveOrUpdate(user);
//		userService.saveAuth(user);
//		return renderResult(Global.TRUE, "操作成功");
	}

	@PostMapping("/{user_code}")
	public boolean update(@Validated @RequestBody User user, @PathVariable("user_code") String userCode) {
		return true;
	}

//	/**
//	 * 停用员工
//	 */
//	@RequestMapping(value = "disable")
//	public String disable(User user) {
//		if (User.isSuperAdmin(user.getUserCode())) {
//			return renderResult(Global.FALSE, "非法操作，不能够操作此用户！");
//		}
//		if(UserUtils.getUser().getUserCode().equals(user.getUserCode())) {
//			return renderResult(Global.FALSE, "停用失败，不允许停用当前用户！");
//		}
//		user.setStatus(Employee.STATUS_DISABLE);
//		userService.updateStatusById(user);
//		return renderResult(Global.TRUE, "停用成功");
//	}
//	
//	/**
//	 * 启用员工
//	 */
//	@RequestMapping(value = "enable")
//	public String enable(User user) {
//		if (User.isSuperAdmin(user.getUserCode())) {
//			return renderResult(Global.FALSE, "非法操作，不能够操作此用户！");
//		}
//		user.setStatus(Employee.STATUS_NORMAL);
//		userService.updateStatusById(user);
//		return renderResult(Global.TRUE, "启用成功");
//	}
//	
//	/**
//	 * 删除员工
//	 */
//	@RequestMapping(value = "delete")
//	public String delete(User user) {
//		if (User.isSuperAdmin(user.getUserCode())) {
//			return renderResult(Global.FALSE, "非法操作，不能够操作此用户！");
//		}
//		if(UserUtils.getUser().getUserCode().equals(user.getUserCode())) {
//			return renderResult(Global.FALSE, "停用失败，不允许停用当前用户！");
//		}
//		if(User.USER_TYPE_NONE.equals(user.getUserType())) {
//			userService.deleteById(user.getUserCode());
//		}else {
//			user.setMgrType(User.MGR_TYPE_NOT_ADMIN);
//			userService.updateMgrType(user);
//		}
//		return renderResult(Global.TRUE, "删除成功！");
//	}
//	
//	
//	/**
//	 * 密码重置
//	 * @param user
//	 * @return
//	 */
//	@RequestMapping(value = "resetpwd")
//	@ResponseBody
//	public String resetpwd(EmpUser user) {
//		if (User.isSuperAdmin(user.getUserCode())) {
//			return renderResult(Global.FALSE, "非法操作，不能够操作此用户！");
//		}
//		userService.updatePassword(user.getUserCode(), null);
//		return renderResult(Global.TRUE, "重置用户密码成功");
//	}
}
