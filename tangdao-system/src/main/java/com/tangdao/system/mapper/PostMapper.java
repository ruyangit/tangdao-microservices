package com.tangdao.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangdao.system.model.domain.Post;

/**
 * 岗位Mapper接口
 * @author ruyang
 * @version 2019-08-23
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {
	
}