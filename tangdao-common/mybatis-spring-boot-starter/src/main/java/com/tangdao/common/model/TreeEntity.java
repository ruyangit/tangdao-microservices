package com.tangdao.common.model;

import java.lang.reflect.Field;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tangdao.common.exception.ServiceException;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.common.reflect.ReflectUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class TreeEntity<T> extends DataEntity<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableField(exist = false)
	protected T parent;

	protected String parentCode;

	protected String parentCodes;

	protected String treeNames;

	protected Integer treeSort;

	@JsonIgnore
	@TableField(exist = false)
	protected String treeName_;

	@TableField(exist = false)
	protected List<T> children;

	protected String treeLeaf;

	protected Integer treeLevel;

	/**
	 * 根节点编码
	 */
	public static final String ROOT_CODE = "0";

	public static final String DEFAULT_TREE_LEAF = "0";

	public static final int DEFAULT_TREE_SORT = 30;

	public TreeEntity() {
		this(null);
	}

	public TreeEntity(String key) {
		this.key = key;
	}

	public boolean isRoot() {
		return ROOT_CODE.equals(getParentCode());
	}

	public void addChild(T node) {
		this.children.add(node);
	}

	@JsonBackReference
	public abstract T getParent();

	@JsonIgnore
	public String getTreeName_() {
		if (StringUtils.isNotBlank(this.treeName_)) {
			return this.treeName_;
		}

		List<Field> fieldList = TableInfoHelper.getAllFields(this.getClass());
		for (Field field : fieldList) {
			if (field.isAnnotationPresent(TreeName.class)) {
				this.treeName_ = (String) ReflectUtils.invokeGetter(this, field.getName());
				break;
			}
		}
		if (StringUtils.isEmpty(this.treeName_)) {
			throw new ServiceException("树状结构需要设置节点名称注解@TreeName");
		}
		return this.treeName_;
	}

	public Integer getTreeLevel() {
		if (this.treeLeaf != null && this.treeLevel == null) {
			this.treeLevel = this.parentCodes != null ? this.parentCodes.replaceAll(",", "").length() - 1 : null;
		}
		return this.treeLevel;
	}

	public Boolean getIsTreeLeaf() {
		return "1".equals(this.treeLeaf);
	}

	public void setTreeLeaf(String treeLeaf) {
		this.treeLeaf = treeLeaf;
	}
}
