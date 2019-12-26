package com.tangdao.system.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangdao.common.model.TreeEntity;
import com.tangdao.common.model.TreeName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 行政区划Entity
 * 
 * @author ruyang
 * @version 2019-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_area")
public class Area extends TreeEntity<Area> {

	private static final long serialVersionUID = 1L;

	@TableId
	private String areaCode; // 区域编码
	@TreeName
	private String areaName; // 区域名称
	private String areaType; // 区域类型

	public Area() {
		super();
	}

	public Area(String areaCode) {
		super(areaCode);
	}

	@Override
	public Area getParent() {
		return parent;
	}
}