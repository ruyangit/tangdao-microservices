package com.tangdao.mybatis.model;

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

	public DataEntity() {
		this(null);
	}

	public DataEntity(String key) {
		super(key);
	}

	public interface SimpleView {
	}

}
