package com.tangdao.system.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangdao.common.model.TreeEntity;
import com.tangdao.common.model.TreeName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 机构Entity
 * @author ruyang
 * @version 2019-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_office")
public class Office extends TreeEntity<Office> {
	
	private static final long serialVersionUID = 1L;
	
	@TableId
	private String officeCode;		// 机构编码
	private String viewCode;		// 机构代码
	@TreeName
	private String officeName;		// 机构名称
	private String fullName;		// 机构全称
	private String officeType;		// 机构类型
	private String leader;		// 负责人
	private String phone;		// 办公电话
	private String address;		// 联系地址
	private String zipCode;		// 邮政编码
	private String email;		// 电子邮箱
	
	public Office() {
		super();
	}

	public Office(String officeCode){
		super(officeCode);
	}
	
	@Override
	public Office getParent() {
		return parent;
	}
}