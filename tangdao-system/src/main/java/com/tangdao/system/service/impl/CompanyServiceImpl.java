package com.tangdao.system.service.impl;

import org.springframework.stereotype.Service;

import com.tangdao.common.service.impl.TreeServiceImpl;
import com.tangdao.system.mapper.CompanyMapper;
import com.tangdao.system.model.domain.Company;
import com.tangdao.system.service.ICompanyService;

/**
 * 公司ServiceImpl
 * @author ruyang
 * @version 2019-08-28
 */
@Service
public class CompanyServiceImpl extends TreeServiceImpl<CompanyMapper, Company> implements ICompanyService{
	
}