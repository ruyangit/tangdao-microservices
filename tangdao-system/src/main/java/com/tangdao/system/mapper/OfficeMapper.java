package com.tangdao.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangdao.system.model.domain.Office;

/**
 * 机构Mapper接口
 * @author ruyang
 * @version 2019-08-24
 */
@Mapper
public interface OfficeMapper extends BaseMapper<Office> {
	
}