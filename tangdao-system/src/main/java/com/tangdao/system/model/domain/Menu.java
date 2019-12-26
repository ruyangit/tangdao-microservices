package com.tangdao.system.model.domain;

import java.util.Collection;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangdao.common.model.TreeEntity;
import com.tangdao.common.model.TreeName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu extends TreeEntity<Menu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单编码
	 */
	@TableId
	private String menuCode;
	/**
	 * 菜单名称
	 */
	@TreeName
	private String menuName;

	/**
	 * 菜单类型（1菜单 2权限 3开发）
	 */
	private String menuType;

	/**
	 * 链接
	 */
	private String menuHref;

	/**
	 * 目标
	 */
	private String menuTarget;

	/**
	 * 图标
	 */
	private String menuIcon;

	/**
	 * 权限标识
	 */
	private String permission;

	/**
	 * 菜单权重
	 */
	private Integer weight;

	/**
	 * 是否显示（1显示 0隐藏）
	 */
	private String isShow;

	// 超级管理员访问的最低权重
	public static Integer SUPER_ADMIN_GET_MENU_MIN_WEIGHT = 40;

	public static Integer WEIGHT_DEFAULT = 20;
	public static Integer WEIGHT_DEFAULT_ADMIN = 60;
	public static Integer WEIGHT_SUPER_ADMIN = 80;

	public static final String TYPE_MENU = "1";
	public static final String TYPE_PERM = "2";

	@TableField(exist = false)
	private Collection<String> defaultRoleCodes;

	@TableField(exist = false)
	private String roleCode;

	@TableField(exist = false)
	private String userCode;

	public Menu() {
		super();
	}

	public Menu(String menuCode) {
		super(menuCode);
	}

	@Override
	public Menu getParent() {
		return this.parent;
	}

}
