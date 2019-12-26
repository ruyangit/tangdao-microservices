package com.tangdao.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangdao.system.model.domain.Company;

/**
 * 公司Mapper接口
 * @author ruyang
 * @version 2019-08-28
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
	
}