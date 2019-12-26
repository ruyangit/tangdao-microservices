package com.tangdao.common.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DataEntity<T> extends BaseEntity<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 基础字段
	@TableField(fill = FieldFill.INSERT)
	protected String createBy; // 新增人
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(fill = FieldFill.INSERT)
	protected Date createTime; // 新增时间

	@TableField(fill = FieldFill.INSERT_UPDATE)
	protected String updateBy; // 更新人

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	protected Date updateTime; // 更新时间

	@TableField(fill = FieldFill.INSERT)
	protected String status; // 状态

	protected String remarks; // 备注

	/**
	 * 正常
	 */
	public static final String STATUS_NORMAL = "0";
	/**
	 * 已删除
	 */
	public static final String STATUS_DELETE = "1";
	/**
	 * 停用
	 */
	public static final String STATUS_DISABLE = "2";
	/**
	 * 冻结
	 */
	public static final String STATUS_FREEZE = "3";
	/**
	 * 审核
	 */
	public static final String STATUS_AUDIT = "4";
	/**
	 * 回退
	 */
	public static final String STATUS_AUDIT_BACK = "5";
	/**
	 * 草稿
	 */
	public static final String STATUS_DRAFT = "9";

	public DataEntity() {
		this(null);
	}

	public DataEntity(String key) {
		super(key);
	}

//	/**
//	 * 新增预处理
//	 */
//	public void preInsert() {
//		this.isNewRecord = true;
//		if (StringUtils.isBlank(this.getKey())) {
//			this.setKey(IdWorker.getIdStr());
//		}
//
//		if (StringUtils.isBlank(getStatus())) {
//			setStatus(STATUS_NORMAL);
//		}
//
//		User user = UserUtils.getUser();
//		if (user != null) {
//			this.setCurrentUser(user);
//			this.createBy = user.getUserCode();
//			this.updateBy = this.createBy;
//		}
//		this.updateTime = new Date();
//		this.createTime = this.updateTime;
//	}
//
//	/**
//	 * 更新预处理
//	 */
//	public void preUpdate() {
//		User user = UserUtils.getUser();
//		if (user != null) {
//			this.setCurrentUser(user);
//			this.updateBy = user.getUserCode();
//		}
//		this.updateTime = new Date();
//	}
	
	public interface SimpleView {
	}

}
