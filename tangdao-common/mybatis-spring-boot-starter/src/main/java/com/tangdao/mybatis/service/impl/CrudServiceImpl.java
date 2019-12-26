package com.tangdao.mybatis.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tangdao.common.exception.ServiceException;
import com.tangdao.mybatis.model.DataEntity;
import com.tangdao.mybatis.service.ICrudService;

/**
 * 	单表操作 封装
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */
public class CrudServiceImpl<M extends BaseMapper<T>, T extends DataEntity<T>> implements ICrudService<T> {

	protected Log log = LogFactory.getLog(getClass());

	@Autowired
	protected M baseMapper;

	@Override
	public M getBaseMapper() {
		return baseMapper;
	}

	/**
	 * 判断数据库操作是否成功
	 *
	 * @page result 数据库操作返回影响条数
	 * @return boolean
	 */
	protected boolean retBool(Integer result) {
		return SqlHelper.retBool(result);
	}

	@SuppressWarnings("unchecked")
	protected Class<T> currentModelClass() {
		return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
	}

	/**
	 * 批量操作 SqlSession
	 */
	protected SqlSession sqlSessionBatch() {
		return SqlHelper.sqlSessionBatch(currentModelClass());
	}

	/**
	 * 释放sqlSession
	 *
	 * @page sqlSession session
	 */
	protected void closeSqlSession(SqlSession sqlSession) {
		SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(currentModelClass()));
	}

	/**
	 * 获取 SqlStatement
	 *
	 * @page sqlMethod ignore
	 * @return ignore
	 */
	protected String sqlStatement(SqlMethod sqlMethod) {
		return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
	}

	@Override
	public boolean save(T entity) {
		return retBool(baseMapper.insert(entity));
	}

	/**
	 * 批量插入
	 *
	 * @page entityList ignore
	 * @page batchSize  ignore
	 * @return ignore
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveBatch(Collection<T> entityList, int batchSize) {
		String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
		try (SqlSession batchSqlSession = sqlSessionBatch()) {
			int i = 0;
			for (T anEntityList : entityList) {
				batchSqlSession.insert(sqlStatement, anEntityList);
				if (i >= 1 && i % batchSize == 0) {
					batchSqlSession.flushStatements();
				}
				i++;
			}
			batchSqlSession.flushStatements();
		}
		return true;
	}

	/**
	 * TableId 注解存在更新记录，否插入一条记录
	 *
	 * @page entity 实体对象
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveOrUpdate(T entity) {
		if (null != entity) {
			Class<?> cls = entity.getClass();
			TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
			Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
			String keyProperty = tableInfo.getKeyProperty();
			Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
			Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
			return StringUtils.checkValNull(idVal) || entity.getIsNewRecord() ? save(entity)
					: updateById(entity);
		}
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
		Assert.notEmpty(entityList, "error: entityList must not be empty");
		Class<?> cls = currentModelClass();
		TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
		Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
		String keyProperty = tableInfo.getKeyProperty();
		Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
		try (SqlSession batchSqlSession = sqlSessionBatch()) {
			int i = 0;
			for (T entity : entityList) {
				Object idVal = ReflectionKit.getMethodValue(cls, entity, keyProperty);
				if (StringUtils.checkValNull(idVal) || entity.getIsNewRecord()) {
					batchSqlSession.insert(sqlStatement(SqlMethod.INSERT_ONE), entity);
				} else {
					MapperMethod.ParamMap<T> page = new MapperMethod.ParamMap<>();
					page.put(Constants.ENTITY, entity);
					batchSqlSession.update(sqlStatement(SqlMethod.UPDATE_BY_ID), page);
				}
				// 不知道以后会不会有人说更新失败了还要执行插入 😂😂😂
				if (i >= 1 && i % batchSize == 0) {
					batchSqlSession.flushStatements();
				}
				i++;
			}
			batchSqlSession.flushStatements();
		}
		return true;
	}

	@Override
	public boolean deleteById(Serializable id) {
		return SqlHelper.retBool(baseMapper.deleteById(id));
	}

	@Override
	public boolean deleteByMap(Map<String, Object> columnMap) {
		Assert.notEmpty(columnMap, "error: columnMap must not be empty");
		return SqlHelper.retBool(baseMapper.deleteByMap(columnMap));
	}

	@Override
	public boolean delete(Wrapper<T> wrapper) {
		return SqlHelper.retBool(baseMapper.delete(wrapper));
	}

	@Override
	public boolean deleteByIds(Collection<? extends Serializable> idList) {
		return SqlHelper.retBool(baseMapper.deleteBatchIds(idList));
	}

	@Override
	public boolean updateById(T entity) {
		return retBool(baseMapper.updateById(entity));
	}

	@Override
	public boolean update(T entity, Wrapper<T> updateWrapper) {
		return retBool(baseMapper.update(entity, updateWrapper));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateBatchById(Collection<T> entityList, int batchSize) {
		Assert.notEmpty(entityList, "error: entityList must not be empty");
		String sqlStatement = sqlStatement(SqlMethod.UPDATE_BY_ID);
		try (SqlSession batchSqlSession = sqlSessionBatch()) {
			int i = 0;
			for (T anEntityList : entityList) {
				MapperMethod.ParamMap<T> page = new MapperMethod.ParamMap<>();
				page.put(Constants.ENTITY, anEntityList);
				batchSqlSession.update(sqlStatement, page);
				if (i >= 1 && i % batchSize == 0) {
					batchSqlSession.flushStatements();
				}
				i++;
			}
			batchSqlSession.flushStatements();
		}
		return true;
	}

	@Override
	public List<T> selectByIds(Collection<? extends Serializable> idList) {
		return baseMapper.selectBatchIds(idList);
	}

	@Override
	public List<T> selectByMap(Map<String, Object> columnMap) {
		return baseMapper.selectByMap(columnMap);
	}

	@Override
	public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
		if (throwEx) {
			return baseMapper.selectOne(queryWrapper);
		}
		return SqlHelper.getObject(log, baseMapper.selectList(queryWrapper));
	}

	@Override
	public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
		return SqlHelper.getObject(log, baseMapper.selectMaps(queryWrapper));
	}

	@Override
	public int count(Wrapper<T> queryWrapper) {
		return SqlHelper.retCount(baseMapper.selectCount(queryWrapper));
	}

	@Override
	public List<T> select(Wrapper<T> queryWrapper) {
		return baseMapper.selectList(queryWrapper);
	}

	@Override
	public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
		return baseMapper.selectPage(page, queryWrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<T> queryWrapper) {
		return baseMapper.selectMaps(queryWrapper);
	}

	@Override
	public <V> List<V> selectObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
		return baseMapper.selectObjs(queryWrapper).stream().filter(Objects::nonNull).map(mapper)
				.collect(Collectors.toList());
	}

	@Override
	public IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper) {
		return baseMapper.selectMapsPage(page, queryWrapper);
	}

	@Override
	public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
		return SqlHelper.getObject(log, selectObjs(queryWrapper, mapper));
	}
	
	@Override
	public T get(Serializable id, boolean isNewRecord) {
		T e = null;
		try {
			if (isNewRecord || id == null || StringUtils.isEmpty(String.valueOf(id))) {
				return this.currentModelClass().newInstance();
			}
			e = this.baseMapper.selectById(id);
			if (isNewRecord && e != null) {
				throw newServiceException(null);
			}
		} catch (Exception e1) {
			throw newServiceException(e1.getMessage());
		}
		if (e == null) {
			try {
				e = this.currentModelClass().newInstance();
			} catch (Exception e1) {
				throw new ServiceException(e1);
			} 
		}
		return e;
	}

	@Override
	public boolean updateStatusById(T entity) {
		if(StringUtils.isEmpty(entity.getKey())) {
			return false;
		}
		T update = null;
		try {
			update = this.currentModelClass().newInstance();
			update.setStatus(entity.getStatus());
			update.setKey(entity.getKey());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			throw newServiceException(e1.getMessage());
		}
		return retBool(baseMapper.updateById(update));
	}
	
	public static ServiceException newServiceException(String message) {
		return new ServiceException(com.tangdao.common.lang.StringUtils.defaultString(message, "校验失败"));
	}

}
