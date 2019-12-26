package com.tangdao.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tangdao.common.service.impl.CrudServiceImpl;
import com.tangdao.system.mapper.DictTypeMapper;
import com.tangdao.system.model.domain.DictType;
import com.tangdao.system.service.IDictDataService;
import com.tangdao.system.service.IDictTypeService;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Service
public class DictTypeServiceImpl extends CrudServiceImpl<DictTypeMapper, DictType> implements IDictTypeService {
	
	@Autowired
	private IDictDataService dictDataService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean delete(DictType dictType) {
		this.dictDataService.deleteByType(dictType.getDictType());
		return super.deleteById(dictType);
	}

}
