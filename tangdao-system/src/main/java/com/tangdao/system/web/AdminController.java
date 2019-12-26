//package com.tangdao.system.web;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.tangdao.common.config.Global;
//import org.tangdao.common.suports.BaseController;
//import org.tangdao.modules.sys.utils.UserUtils;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.tangdao.system.model.domain.Employee;
//import com.tangdao.system.model.vo.EmpUser;
//import com.tangdao.system.service.IRoleService;
//import com.tangdao.system.service.IUserService;
//
///**
// * <p>
// * 用户表 前端控制器
// * </p>
// *
// * @author ruyang
// * @since 2019-07-02
// */
//@Controller
//@RequestMapping("${tangdao.context-path}/sys/admin")
//public class AdminController{
//
//	@Autowired
//	private IUserService userService;
//
//	@Autowired
//	private IRoleService roleService;
//	
//	@PostMapping("/listData")
//	public @ResponseBody IPage<User> listData(User user, HttpServletRequest request) {
//		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//		if(StringUtils.isNotBlank(user.getStatus())) {
//			queryWrapper.eq("status", user.getStatus());
//		}
//		if(StringUtils.isNotBlank(user.getUsername())) {
//			queryWrapper.like("username", user.getUsername());
//		}
//		queryWrapper.eq("mgr_type", User.MGR_TYPE_DEFAULT_ADMIN);
//		return this.userService.page(user.getPage(), queryWrapper);
//	}
//
//	@GetMapping("/form")
//	public String form(User user, String op, Model model) {
//		user.setRoles(roleService.findByUserCode(user.getUserCode()));
//		model.addAttribute("op", op);
//		model.addAttribute("user", user);
//		return "modules/sys/adminForm";
//	}
//
//	@PostMapping(value = "save")
//	public @ResponseBody String save(@Validated User user, String oldUsername, String op, Model model) {
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
//		// 如果修改的是当前用户，则清除当前用户缓存
//		if (user.getUserCode().equals(UserUtils.getUser().getUserCode())) {
////			UserUtils.clearCache();
//		}
//
//		return renderResult(Global.TRUE, "操作成功");
//	}
//	
//	/**
//	 * 停用员工
//	 */
//	@RequestMapping(value = "disable")
//	public @ResponseBody String disable(User user) {
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
//	public @ResponseBody String enable(User user) {
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
//	public @ResponseBody String delete(User user) {
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
//}
