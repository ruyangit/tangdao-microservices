//package com.tangdao.system.web;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.tangdao.common.config.Global;
//import org.tangdao.common.suports.BaseController;
//import org.tangdao.modules.sys.utils.UserUtils;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.tangdao.system.service.IMenuService;
//
///**
// * <p>
// * 菜单表 前端控制器
// * </p>
// *
// * @author ruyang
// * @since 2019-07-02
// */
//@Controller
//@RequestMapping("${tangdao.context-path}/sys/menu")
//public class MenuController extends BaseController {
//
//	@Autowired
//	IMenuService menuService;
//
//	@ModelAttribute
//	public Menu get(String menuCode, boolean isNewRecord) {
//		return menuService.get(menuCode, isNewRecord);
//	}
//
//	@RequestMapping(value = "list")
//	public String list(Menu menu, Model model) {
//		return "modules/sys/menuList";
//	}
//
//	@RequestMapping(value = "listData")
//	public @ResponseBody List<Menu> list(Menu menu) {
//
//		QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>();
//		if (StringUtils.isBlank(menu.getParentCode())) {
//			menu.setParentCode(Menu.ROOT_CODE);
//		}
//		queryWrapper.eq("parent_code", menu.getParentCode());
//		
//		if (StringUtils.isNotBlank(menu.getMenuName())) {
//			queryWrapper.likeRight("menu_name", menu.getMenuName());
//		}
//
//		queryWrapper.orderByAsc("tree_sort", "menu_code");
//		return this.menuService.select(queryWrapper);
//	}
//
//	@RequestMapping(value = "form")
//	public String form(Menu menu, Model model, String op) {
//		if (StringUtils.isNotBlank(menu.getParentCode())) {
//			menu.setParent(this.menuService.get(menu.getParentCode()));
//		}
//		if (menu.getParent() == null) {
//			menu.setParent(new Menu(Menu.ROOT_CODE));
//		}
//		model.addAttribute("menu", menu);
//		model.addAttribute("op", op);
//		return "modules/sys/menuForm";
//	}
//
//	@PreAuthorize("hasAuthority('sys:menu:edit')")
//	@PostMapping(value = "save")
//	public @ResponseBody String save(@Validated Menu menu) {
//		if(!UserUtils.getUser().isSuperAdmin()) {
//			return this.renderResult(Global.FALSE, "越权操作，只有超级管理员才能修改此数据");
//		}
//		
//		menuService.saveOrUpdate(menu);
//		return renderResult(Global.TRUE, "保存成功");
//	}
//
//	@PreAuthorize("hasAuthority('sys:menu:edit')")
//	@ResponseBody
//	@PostMapping(value = "delete")
//	public String delete(Menu menu) {
//		if(!UserUtils.getUser().isSuperAdmin()) {
//			return this.renderResult(Global.FALSE, "越权操作，只有超级管理员才能修改此数据");
//		}
//		menuService.delete(menu);
//		return renderResult(Global.TRUE, "删除成功");
//	}
//
////	@PreAuthorize("hasAuthority('sys:menu:edit')")
////	@ResponseBody
////	@PostMapping(value = "disable")
////	public String disable(Menu menu) {
////		Example example = new Example(Menu.class);
////		example.and().andEqualTo("status", Menu.STATUS_NORMAL);
////		example.and().andLike("parentIds", "%," + menu.getmenuCode() + ",%");
////		int count = menuService.selectCountByExample(example);
////		if (count > 0) {
////			return renderResult(Global.TRUE, "该菜单包含未停用的子菜单！");
////		}
////		menu.setStatus(Menu.STATUS_DISABLE);
////		menuService.updateByPrimaryKeySelective(menu);
////		return renderResult(Global.TRUE, "停用成功");
////	}
////
////	@PreAuthorize("hasAuthority('sys:menu:edit')")
////	@ResponseBody
////	@PostMapping(value = "enable")
////	public String enable(Menu menu) {
////		menu.setStatus(Menu.STATUS_NORMAL);
////		menuService.updateByPrimaryKeySelective(menu);
////		return renderResult(Global.TRUE, "启用成功");
////	}
//
////	@ResponseBody
////	@PostMapping(value = "refresh")
////	public String refresh() {
////		UserUtils.removeCacheByKeyPrefix("menuList");
////		UserUtils.removeCache("authInfo_" + UserUtils.getSession().getId());
////		return renderResult(Global.TRUE, "缓存更新完成");
////	}
//
//	@RequestMapping(value = "treeData")
//	public @ResponseBody List<Map<String, Object>> treeData(Menu menu, String excludeCode, String isShowCode) {
//		QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>();
//		if (StringUtils.isNotBlank(excludeCode)) {
//			queryWrapper.ne("menu_code", excludeCode);
//			queryWrapper.notLike("parent_codes", excludeCode);
//		}
//
//		if (StringUtils.isNotBlank(menu.getIsShow())) {
//			queryWrapper.eq("is_show", menu.getIsShow());
//		}
//		queryWrapper.ne("status", Menu.STATUS_DELETE);
//
//		queryWrapper.orderByAsc("tree_sort");
//		List<Menu> sourceList = menuService.select(queryWrapper);
//
//		List<Map<String, Object>> targetList = ListUtils.newArrayList();
//		Map<String, Object> tempMap = null;
//		for (Menu sMenu : sourceList) {
//			tempMap = MapUtils.newHashMap();
//			tempMap.put("id", sMenu.getMenuCode());
//			tempMap.put("pId", sMenu.getParentCode());
//			tempMap.put("name", StringUtils.getTreeNodeName(isShowCode, sMenu.getMenuCode(), sMenu.getMenuName()));
//			targetList.add(tempMap);
//		}
//		return targetList;
//	}
//	
//	@RequestMapping({"updateTreeSort"})
//	@ResponseBody
//	public String updateTreeSort(String[] ids, Integer[] sorts) {
//		if (!UserUtils.getUser().isSuperAdmin()) {
//			return this.renderResult(Global.FALSE, "越权操作，只有超级管理员才能修改此数据");
//		} else {
//    	  for (int i = 0;i<ids.length;i++) {
//    		  Menu menu = new Menu(ids[i]);
//    		  menu.setTreeSort(sorts[i]);
//    		  this.menuService.updateTreeSort(menu);
//    	  }
//    	  return this.renderResult(Global.TRUE, "保存成功");
//      }
//   }
//
//}
