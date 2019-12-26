package com.tangdao.system.service.impl;

import org.springframework.stereotype.Service;

import com.tangdao.common.service.impl.TreeServiceImpl;
import com.tangdao.system.mapper.AreaMapper;
import com.tangdao.system.model.domain.Area;
import com.tangdao.system.service.IAreaService;

/**
 * 行政区划ServiceImpl
 * @author ruyang
 * @version 2019-09-27
 */
@Service
public class AreaServiceImpl extends TreeServiceImpl<AreaMapper, Area> implements IAreaService{
		
}