package com.tangdao.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tangdao.common.service.impl.CrudServiceImpl;
import com.tangdao.system.mapper.ConfigMapper;
import com.tangdao.system.model.domain.Config;
import com.tangdao.system.service.IConfigService;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Service
public class ConfigServiceImpl extends CrudServiceImpl<ConfigMapper, Config> implements IConfigService {
//
//	@Autowired
//	private JedisUtils jedisUtils;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public Config getConfigByKey(String configKey) {
//		try {
//			Object obj = jedisUtils.getHashEntries(SysRedisConstant.RED_SYS_CONFIG_LIST).get(configKey);
//			if (obj != null) {
//				return (Config) obj;
//			}
//		} catch (Exception e) {
//			logger.warn("REDIS 加载失败，将于DB加载", e);
//		}
//		Config config = super.getOne(Wrappers.<Config>lambdaQuery().eq(Config::getConfigKey, configKey));
//		if (config != null) {
//			loadToRedis(config);
//		}
//		return config;
		return null;
	}

	@Override
	public boolean reloadToRedis() {
//		List<Config> list = super.select(Wrappers.<Config>lambdaQuery().eq(Config::getStatus, DictData.STATUS_NORMAL));
//		if (ListUtils.isEmpty(list)) {
//			logger.warn("缓冲系统配置失败，系统配置可用数据为空，请排查");
//			return false;
//		}
//
//		try {
//			jedisUtils.delete(SysRedisConstant.RED_SYS_CONFIG_LIST);
//			Map<Object, Object> map = MapUtils.newLinkedHashMap();
//			for (Config config : list) {
//				map.put(config.getConfigKey(), config);
//			}
//
//			jedisUtils.add(SysRedisConstant.RED_SYS_CONFIG_LIST, map);
//			return true;
//		} catch (Exception e) {
//			logger.warn("REDIS 重载数据失败", e);
//			return false;
//		}
		return false;
	}

	/**
	 * 加载到REDIS
	 * 
	 * @param config 可用配置
	 */
	private void loadToRedis(Config config) {
//		try {
//			jedisUtils.add(SysRedisConstant.RED_SYS_CONFIG_LIST, config.getConfigKey(), config);
//		} catch (Exception e) {
//			logger.warn("Redis 加载失败", e);
//		}
	}

}
