package com.tangdao.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tangdao.common.collect.ListUtils;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.common.service.impl.TreeServiceImpl;
import com.tangdao.system.mapper.MenuMapper;
import com.tangdao.system.model.domain.Config;
import com.tangdao.system.model.domain.Menu;
import com.tangdao.system.model.domain.Role;
import com.tangdao.system.model.domain.User;
import com.tangdao.system.service.IConfigService;
import com.tangdao.system.service.IMenuService;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Service
public class MenuServiceImpl extends TreeServiceImpl<MenuMapper, Menu> implements IMenuService {

//	@Autowired
//	private JedisUtils jedisUtils;
	
	@Autowired
	private IConfigService configService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 根据角色获取菜单
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> findByRoleMenu(Menu menu) {
		return this.baseMapper.findByRoleMenu(menu);
	}

	/**
	 * 根据用户获取菜单
	 * 
	 * @param menu
	 * @param isFindParentAndChild 是否查找父子节点
	 * @return
	 */
	public List<Menu> findByUserMenu(Menu menu, boolean isFindParentAndChild) {
		if (isFindParentAndChild) {
			List<Menu> menuList = findByStatusNormal(menu);
			return findParentAndChild(menuList, this.baseMapper.findByUserMenu(menu));
		}
		return this.baseMapper.findByUserMenu(menu);
	}

	/**
	 * 查询正常菜单
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> findByStatusNormal(Menu menu) {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>();
		queryWrapper.eq("status", Menu.STATUS_NORMAL);
		if (menu.getMenuType() != null) {
			queryWrapper.eq("menuType", menu.getMenuType());
		}
		queryWrapper.orderByAsc("tree_sort");
		return this.select(queryWrapper);
	}

	/**
	 * 获取菜单的所有父节点，所有子节点
	 * 
	 * @param menuList     所有菜单
	 * @param roleMenuList 授权菜单
	 * @return
	 */
	public List<Menu> findParentAndChild(List<Menu> menuList, List<Menu> roleMenuList) {
		List<Menu> sourcelist = ListUtils.newArrayList();
		sourcelist.addAll(roleMenuList);
		for (Menu roleMenu : roleMenuList) {
			// 查找父节点
			String[] parentCodes = roleMenu.getParentCodes().split(",");
			for (String parentCode : parentCodes) {
				for (Menu menu : menuList) {
					if (parentCode.equalsIgnoreCase(menu.getMenuCode())) {
						sourcelist.add(menu);
					}
				}
			}
		}
		return sourcelist;
	}

	public boolean reloadMenuTreeNameToRedis() {
//		List<Menu> menuList = this.findByStatusNormal(new Menu());
//		if (ListUtils.isEmpty(menuList)) {
//			logger.warn("缓冲权限名称失败，数据为空，请排查");
//			return false;
//		}
//		try {
//			jedisUtils.delete(SysRedisConstant.RED_MENU_NAME_PATH);
//			Map<Object, Object> map = MapUtils.newLinkedHashMap();
//			for (Menu menu : menuList) {
//				String menuHref = StringUtils.substringBefore(menu.getMenuHref(), "?");
//				if (StringUtils.isNotBlank(menuHref)) {
//					if (StringUtils.endsWith(menuHref, "/")) {
//						menuHref = StringUtils.substring(menuHref, menuHref.length() - 1);
//					}
//					if (StringUtils.isNotBlank(menuHref)) {
//						map.put(menuHref, menu.getTreeNames());
//					}
//					menuHref = StringUtils.substringBeforeLast(menuHref, "/");
//					if (StringUtils.isNotBlank(menuHref)) {
//						map.put(menuHref, menu.getTreeNames());
//					}
//				}
//				if (!StringUtils.isNotBlank(menu.getPermission()))
//					continue;
//				String[] pers = StringUtils.split(menu.getPermission(), ",");
//				for (String p : pers) {
//					if (StringUtils.isNotBlank(p)) {
//						map.put(p, menu.getTreeNames());
//						p = StringUtils.substringBeforeLast(p, ":");
//						if (StringUtils.isNotBlank(p)) {
//							map.put(p, menu.getTreeNames());
//						}
//					}
//				}
//			}
//			jedisUtils.add(SysRedisConstant.RED_MENU_NAME_PATH, map);;
//			return true;
//		} catch (Exception e) {
//			logger.warn("REDIS 存储数据失败", e);
//			return false;
//		}
		return false;
	}

	public String getMenuNamePath(String href, String permission) {

//		Map<String, String> menuNamePathMap = MapUtils.newHashMap();
//		try {
//			Map<Object, Object> entries = jedisUtils.getHashEntries(SysRedisConstant.RED_MENU_NAME_PATH);
//			if (MapUtils.isNotEmpty(entries)) {
//				for (Object key : entries.keySet()) {
//					menuNamePathMap.put(key.toString(), entries.get(key).toString());
//				}
//			}
//		} catch (Exception e) {
//			logger.warn("REDIS 加载失败，将于DB加载", e);
//		}
//
//		if (StringUtils.endsWith(href, "/")) {
//			String string = href;
//			href = StringUtils.substring(string, string.length() - 1);
//		}
//		String s;
//		if ((s = menuNamePathMap.get(href)) == null) {
//			String[] pers = StringUtils.split(permission, ",");
//			for (String p : pers) {
//				s = menuNamePathMap.get(p);
//				if (StringUtils.isNotBlank(s)) {
//					return s;
//				}
//			}
//		}
//		if (s == null) {
//			s = menuNamePathMap.get(StringUtils.substringBeforeLast(href, "/"));
//		}
//		if (s == null) {
//			return StringUtils.EMPTY;
//		}
//		return s;
		return null;
	}
	
	/**
	 * 	获取权限数据源
	 * @param user
	 * @param parentCode
	 * @return
	 */
	public List<Menu> getMenuByParentCode(User user, String parentCode) {
		List<Menu> menus = null;
		if (User.isSuperAdmin(user.getUserCode())) {
			QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>();
			queryWrapper.ne("status", Menu.STATUS_DELETE);
			queryWrapper.ge("weight", Menu.SUPER_ADMIN_GET_MENU_MIN_WEIGHT); // 管理员获取的最小权重菜单，（用于有些菜单管理员不可查看，如财务报表等）

			queryWrapper.orderByAsc("tree_sort");
			menus = this.select(queryWrapper);
		} else {
			// 获取用户类型指定默认角色
			Menu menu = new Menu();
			String defaultRoleCodes = configService.getValueByKey("sys.user.defaultRoleCodes." + user.getUserType());
			if (!User.USER_TYPE_NONE.equals(user.getUserType()) && StringUtils.isNotBlank(defaultRoleCodes)) {
				menu.setDefaultRoleCodes(Arrays.asList(StringUtils.split(defaultRoleCodes, ",")));
			}
			// 管理员角色追加
			if (user.isAdmin()) {
				Collection<String> roleCodes = ListUtils.newArrayList();
				roleCodes.addAll(menu.getDefaultRoleCodes());
				roleCodes.add(Role.DEFAULT_ADMIN_ROLE_CODE);
				menu.setDefaultRoleCodes(roleCodes);
			}
			menu.setUserCode(user.getUserCode());
			menu.setParentCode(parentCode);
			menus = this.findByUserMenu(menu, false);
		}
		return menus;
	}
}
