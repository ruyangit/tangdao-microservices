package com.tangdao.system.service.impl;

import org.springframework.stereotype.Service;

import com.tangdao.common.service.impl.TreeServiceImpl;
import com.tangdao.system.mapper.OfficeMapper;
import com.tangdao.system.model.domain.Office;
import com.tangdao.system.service.IOfficeService;

/**
 * 机构ServiceImpl
 * @author ruyang
 * @version 2019-08-24
 */
@Service
public class OfficeServiceImpl extends TreeServiceImpl<OfficeMapper, Office> implements IOfficeService{
		
}