package com.tangdao.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangdao.system.model.domain.DictType;

/**
 * <p>
 * 字典类型表 Mapper 接口
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Mapper
public interface DictTypeMapper extends BaseMapper<DictType> {

}
