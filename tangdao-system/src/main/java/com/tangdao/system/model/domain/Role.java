package com.tangdao.system.model.domain;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangdao.common.model.DataEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends DataEntity<Role> {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色编码
	 */
	@TableId
	@NotNull(message = "角色编码不能为空")
	private String roleCode;

	/**
	 * 角色名称
	 */
	@NotNull(message = "角色名称不能为空")
	private String roleName;

	/**
	 * 角色排序（升序）
	 */
	@NotNull(message = "角色排序不能为空")
	private Integer roleSort;

	/**
	 * 数据范围设置（0未设置 1全部数据 2自定义数据）
	 */
	private String dataScope;

	/**
	 * 用户类型
	 */
	private String userType;

	// 默认管理员角色
	public static final String DEFAULT_ADMIN_ROLE_CODE = "corpAdmin";

	public static final String DATA_SCOPE_NONE = "0";
	public static final String DATA_SCOPE_ALL = "1";
	public static final String DATA_SCOPE_CUSTOM = "2";

	@TableField(exist = false)
	private String userCode;

}
