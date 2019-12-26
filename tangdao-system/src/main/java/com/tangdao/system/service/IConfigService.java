package com.tangdao.system.service;

import com.tangdao.common.service.ICrudService;
import com.tangdao.system.model.domain.Config;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
public interface IConfigService extends ICrudService<Config> {
	
	public Config getConfigByKey(String configKey);

	public boolean reloadToRedis();
}
