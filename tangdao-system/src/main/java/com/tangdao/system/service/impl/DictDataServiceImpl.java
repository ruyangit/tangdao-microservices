package com.tangdao.system.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tangdao.common.service.impl.CrudServiceImpl;
import com.tangdao.system.mapper.DictDataMapper;
import com.tangdao.system.model.domain.DictData;
import com.tangdao.system.service.IDictDataService;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Service
public class DictDataServiceImpl extends CrudServiceImpl<DictDataMapper, DictData> implements IDictDataService {

//	@Autowired
//	private JedisUtils jedisUtils;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public Map<String, List<DictData>> getDictDataList() {

//		Map<String, List<DictData>> dictDataMap = MapUtils.newHashMap();
//		try {
//			Map<Object, Object> entries = jedisUtils.getHashEntries(SysRedisConstant.RED_SYS_DICT_DATA_LIST);
//			if (MapUtils.isNotEmpty(entries)) {
//				for (Object key : entries.keySet()) {
//					List<DictData> list = (List<DictData>) entries.get(key);
//					dictDataMap.put(key.toString(), list);
//				}
//			}
//		} catch (Exception e) {
//			logger.warn("REDIS 加载失败，将于DB加载", e);
//		}
//
//		if (dictDataMap.size() == 0) {
//			dictDataMap = getDictDataMap();
//			if (dictDataMap.size() > 0) {
//				loadToRedis(dictDataMap);
//			}
//		}
//
//		return dictDataMap;
		return null;
	}

	public List<DictData> getDictDataList(String dictType) {
		Map<String, List<DictData>> dictDataMap = getDictDataList();
		List<DictData> list = null;
		if (dictDataMap != null && dictDataMap.size() != 0) {
			list = dictDataMap.get(dictType);
		}
		if (list == null) {
			list = super.select(Wrappers.<DictData>lambdaQuery().eq(DictData::getStatus, DictData.STATUS_NORMAL)
					.eq(DictData::getDictType, dictType).orderByAsc(DictData::getDictSort));
			if (list != null) {
				loadToRedis(dictType, list);
			}
		}
		return list;
	}

	public void loadToRedis(String dictType, List<DictData> list) {
//		try {
//			jedisUtils.add(SysRedisConstant.RED_SYS_DICT_DATA_LIST, dictType, list);
//		} catch (Exception e) {
//			logger.warn("Redis 加载失败", e);
//		}
	}

	public boolean loadToRedis(Map<String, List<DictData>> dictDataMap) {
//		if (MapUtils.isEmpty(dictDataMap)) {
//			logger.warn("缓冲失败，可用数据为空，请排查");
//			return false;
//		}
//
//		try {
//			jedisUtils.delete(SysRedisConstant.RED_SYS_DICT_DATA_LIST);
//
//			Map<Object, Object> map = MapUtils.newLinkedHashMap();
//			dictDataMap.keySet().stream().forEach(key -> {
//				map.put(key, dictDataMap.get(key));
//			});
//			jedisUtils.add(SysRedisConstant.RED_SYS_DICT_DATA_LIST, map);
//			return true;
//		} catch (Exception e) {
//			logger.warn("REDIS 重载数据失败", e);
//			return false;
//		}
		return false;
	}

	@Override
	public boolean reloadToRedis() {
		Map<String, List<DictData>> dictDataMap = getDictDataMap();
		return loadToRedis(dictDataMap);
	}

	public Map<String, List<DictData>> getDictDataMap() {
//		List<DictData> list = super.select(Wrappers.<DictData>lambdaQuery()
//				.eq(DictData::getStatus, DictData.STATUS_NORMAL).orderByAsc(DictData::getDictSort));
//		if (ListUtils.isEmpty(list)) {
//			logger.warn("缓冲失败，可用数据为空，请排查");
//			return null;
//		}
//		Map<String, List<DictData>> dictDataMap = MapUtils.newLinkedHashMap();
//		List<DictData> targetList = null;
//		for (DictData dd : list) {
//			String dictType = dd.getDictType();
//			if (dictDataMap.get(dictType) == null) {
//				targetList = ListUtils.newLinkedList();
//			} else {
//				targetList = dictDataMap.get(dictType);
//			}
//			targetList.add(dd);
//			dictDataMap.put(dictType, targetList);
//		}
//		return dictDataMap;
		return null;
	}

	@Override
	public boolean deleteByType(String dictType) {
		return super.delete(Wrappers.<DictData>lambdaQuery().eq(DictData::getDictType, dictType));
	}
}
