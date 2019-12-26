package com.tangdao.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangdao.system.model.domain.DictData;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {

}
