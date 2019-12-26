package com.tangdao.system.model.domain;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangdao.common.model.DataEntity;

/**
 * 岗位Entity
 * @author ruyang
 * @version 2019-08-23
 */
@TableName("sys_post")
public class Post extends DataEntity<Post> {
	
	private static final long serialVersionUID = 1L;
	
	@TableId
	private String postCode;		// 岗位编码
	private String postName;		// 岗位名称
	private String postType;		// 岗位分类
	private Integer postSort;		// 岗位排序
	
	public Post() {
		super();
	}

	public Post(String postCode){
		super(postCode);
	}
	
	@Length(min=0, max=64, message="岗位编码长度必须介于 0 和 64 之间")
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Length(min=0, max=100, message="岗位名称长度必须介于 0 和 100 之间")
	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	@Length(min=0, max=1, message="岗位分类长度必须介于 0 和 1 之间")
	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	public Integer getPostSort() {
		return postSort;
	}

	public void setPostSort(Integer postSort) {
		this.postSort = postSort;
	}
	
}