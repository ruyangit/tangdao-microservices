package com.tangdao.mybatis.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.tangdao.common.collect.ListUtils;
import com.tangdao.common.collect.MapUtils;
import com.tangdao.common.exception.ServiceException;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.common.reflect.ReflectUtils;
import com.tangdao.mybatis.model.TreeEntity;
import com.tangdao.mybatis.service.ITreeService;

/**
 *	 封装结构化数据处理
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */
public class TreeServiceImpl<M extends BaseMapper<T>, T extends TreeEntity<T>> extends CrudServiceImpl<M, T> implements ITreeService<T> {

	@Override
	public void convertTreeSort(List<T> sourceList, List<T> targetList, String parentId) {
		sourceList.stream().filter(tree -> tree.getParentCode() != null && tree.getParentCode().equals(parentId))
				.forEach(tree -> {
					targetList.add(tree);
					// 判断是否还有子节点, 有则继续获取子节点
					sourceList.stream().filter(
							child -> child.getParentCode() != null && child.getParentCode().equals(tree.getKey()))
							.peek(child -> convertTreeSort(targetList, sourceList, tree.getKey())).findFirst();
				});
	}

	@Override
	public void convertChildList(List<T> sourceList, List<T> targetList, String parentId) {

		// 构建一个Map,把所有原始数据的ID作为Key,原始数据对象作为VALUE
		Map<String, T> dtoMap = MapUtils.newLinkedHashMap();
		for (T tree : sourceList) {
			// 原始数据对象为Node，放入dtoMap中。
			tree.setChildren(null);
			dtoMap.put(tree.getKey(), tree);
		}

		for (Map.Entry<String, T> entry : dtoMap.entrySet()) {
			T node = entry.getValue();
			String tParentId = node.getParentCode();
			if (dtoMap.get(tParentId) == null) {
				// 如果是顶层节点，直接添加到结果集合中
				targetList.add(node);
			} else {
				// 如果不是顶层节点，有父节点，然后添加到父节点的子节点中
				T parent = dtoMap.get(tParentId);
				if (parent.getChildren() == null) {
					parent.setChildren(ListUtils.newArrayList());
				}
				parent.addChild(node);
			}
		}

	}
	
	@Override
	public T get(Serializable id, boolean isNewRecord) {
		T t = super.get(id, isNewRecord);
		if(StringUtils.isNotBlank(t.getParentCode())&&!TreeEntity.ROOT_CODE.equals(t.getParentCode())) {
			t.setParent(super.get(t.getParentCode()));
		}
		return t;
	}

	@Override
	public boolean saveOrUpdate(T entity) {
		if (null == entity) {
			return false;
		}
		//旧的对象
		T oldEntity = this.get(entity.getKey());
		T parentEntity = null;
		if(entity.getParent()!=null) {
			entity.setParentCode(entity.getParent().getKey());
		}
		if (StringUtils.isNotBlank(entity.getParentCode()) && !TreeEntity.ROOT_CODE.equals(entity.getParentCode())) {
			parentEntity = this.get(entity.getParentCode());
		}
		//实例一个新的
		@SuppressWarnings("unchecked")
		Class<T> entityClass = ReflectUtils.getClassGenricType(getClass(), 1);
		if (parentEntity == null || StringUtils.isBlank(entity.getParentCode()) || TreeEntity.ROOT_CODE.equals(entity.getParentCode())) {
			try {
				// 构造一个父节点
				parentEntity = entityClass.getConstructor(String.class).newInstance(TreeEntity.ROOT_CODE);
			} catch (Exception e) {
				throw new ServiceException("初始化父类对象", e);
			}
			parentEntity.setParentCodes(StringUtils.EMPTY);
			parentEntity.setTreeNames(StringUtils.EMPTY);

			// 设置保存对象的父节点
			entity.setParentCode(parentEntity.getKey());
		}
		//设置父类对象
		entity.setParent(parentEntity);

		String oldParentCodes = entity.getParentCodes();
		String oldTreeNames = entity.getTreeNames();

		entity.setParentCodes(parentEntity.getParentCodes() + parentEntity.getKey() + ",");
		entity.setTreeLevel(entity.getParentCodes().replaceAll("[^,]", "").length() - 1);

		String treeId = entity.getKey();
		String treeName = entity.getTreeName_();

		if (treeName == null) {
			treeName = StringUtils.EMPTY;
		}

		if (entity.isRoot()) {
			entity.setTreeNames(treeName);
		} else {
			entity.setTreeNames(parentEntity.getTreeNames() + "/" + treeName);
		}

		if (StringUtils.isBlank(treeId)||entity.getIsNewRecord()) {
			// 排序处理
			if (entity.getTreeSort() == null) {
				entity.setTreeSort(T.DEFAULT_TREE_SORT);
			}
			entity.setTreeLeaf(T.DEFAULT_TREE_LEAF);
			this.save(entity);
		} else {
			this.updateById(entity);
		}
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		queryWrapper.like("parent_codes", entity.getKey());
		List<T> list = this.select(queryWrapper);
		for (T e : list) {
			if (e.getParentCodes() != null && oldParentCodes != null) {
				e.setParentCodes(e.getParentCodes().replace(oldParentCodes, entity.getParentCodes()));
				e.setTreeNames(e.getTreeNames().replace(oldTreeNames, entity.getTreeNames()));
				e.setTreeLevel(e.getParentCodes().replaceAll("[^,]", "").length() - 1);
				preUpdateChild(entity, e);
				this.updateById(e);
			}
		}
		// 更新原始父节点的对象
		if (oldEntity != null && oldEntity.getParentCode()!=null&&!TreeEntity.ROOT_CODE.equals(oldEntity.getParentCode())) {
			try {
				// 构造一个父节点
				parentEntity = entityClass.getConstructor(String.class).newInstance(TreeEntity.ROOT_CODE);
			} catch (Exception e) {
				throw new ServiceException("初始化父类对象", e);
			}
			parentEntity.setKey(oldEntity.getParentCode());
			this.updateTreeLeaf(parentEntity);
		}
		if (entity != null && entity.getParent() != null && !StringUtils.equals(entity.getParentCode(),  oldEntity.getParentCode())) {
			this.updateTreeLeaf(entity.getParent());
		}

		return true;

	}

	/**
	 * 预留接口，用户更新子节前调用
	 * 
	 * @param childEntity
	 */
	protected void preUpdateChild(T entity, T childEntity) {

	}

	@Transactional(readOnly = false, isolation = Isolation.READ_UNCOMMITTED)
	public void updateTreeLeaf(T entity) {
		if (TreeEntity.ROOT_CODE.equals(entity.getKey())) {
			return;
		}
		TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
		StringBuffer sql = new StringBuffer();
		sql.append(" tree_leaf = (");
		sql.append(" select tree_leaf from(");
		sql.append(" SELECT (case when count(1) > 0 then '0' else '1' end) tree_leaf");
		sql.append(" from " + tableInfo.getTableName());
		sql.append(" where status='0'").append(" and parent_code = '").append(entity.getKey()).append("'");
		sql.append(" ) a )");
		UpdateWrapper<T> updateWrapper = new UpdateWrapper<T>();
		updateWrapper.setSql(sql.toString());
		updateWrapper.eq(tableInfo.getKeyColumn(), entity.getKey());
		this.update(updateWrapper);
	}
	
	@Transactional(readOnly = false, isolation = Isolation.READ_UNCOMMITTED)
	public void delete(T entity) {
		if (StringUtils.isBlank(entity.getKey())) {	
            return;	
        }
		this.deleteById(entity);
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		queryWrapper.like("parent_codes", entity.getKey());
		this.delete(queryWrapper);
		
		T parent = this.get(entity.getParentCode());
		if (parent != null && parent.getKey()!=null) {	
            this.updateTreeLeaf(parent);	
        }
	}

	@Transactional(readOnly = false, isolation = Isolation.READ_UNCOMMITTED)
	public void updateTreeSort(T entity) {
		// TODO Auto-generated method stub
		if (entity.getTreeSort() == null) {	
            entity.setTreeSort(30);	
        }
		TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
		UpdateWrapper<T> updateWrapper = new UpdateWrapper<T>();
		updateWrapper.setSql(" tree_sort = " + entity.getTreeSort());
		updateWrapper.eq(tableInfo.getKeyColumn(), entity.getKey());
		this.update(updateWrapper);
		
	}

}
