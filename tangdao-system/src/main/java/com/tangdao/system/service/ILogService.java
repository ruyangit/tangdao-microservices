package com.tangdao.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangdao.system.model.domain.Log;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
public interface ILogService{
	
	public void clearAll();

	public void insertLog(Log entity);
	
	public Log get(String id);
	
	public IPage<Log> page(IPage<Log> page, Wrapper<Log> queryWrapper);
}
