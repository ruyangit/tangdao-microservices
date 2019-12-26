package com.tangdao.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.system.mapper.LogMapper;
import com.tangdao.system.model.domain.Log;
import com.tangdao.system.service.ILogService;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Service
public class LogServiceImpl  implements ILogService {

	@Autowired
	private LogMapper logMapper;
	
	public void clearAll() {
		this.logMapper.delete(null);
	}
	
	/**
	 * 不使用数据库事务，执行插入日志
	 */
	public void insertLog(Log entity) {
		this.logMapper.insert(entity);
	}
	
	public Log get(String id) {
		if(StringUtils.isBlank(id)) {
			return new Log();
		}
		Log log = this.logMapper.selectById(id);
		if(log!=null) {
			return log;
		}
		return new Log();
	}
	
	public IPage<Log> page(IPage<Log> page, Wrapper<Log> queryWrapper){
		return this.logMapper.selectPage(page, queryWrapper);
	}
}
