package com.tangdao.system.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangdao.common.model.TreeEntity;
import com.tangdao.common.model.TreeName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 公司Entity
 * 
 * @author ruyang
 * @version 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_company")
public class Company extends TreeEntity<Company> {

	private static final long serialVersionUID = 1L;

	@TreeName
	private String companyName; // 公司名称
	@TableId
	private String companyCode; // 公司编码
	private String fullName; // 公司全称
	private String viewCode; // 公司代码
	private String areaCode; // 区域编码

	public Company() {
		super();
	}

	public Company(String companyCode) {
		super(companyCode);
	}

	@Override
	public Company getParent() {
		return parent;
	}
}