package com.tangdao.system.service.impl;

import org.springframework.stereotype.Service;

import com.tangdao.common.service.impl.CrudServiceImpl;
import com.tangdao.system.mapper.PostMapper;
import com.tangdao.system.model.domain.Post;
import com.tangdao.system.service.IPostService;

/**
 * 岗位ServiceImpl
 * @author ruyang
 * @version 2019-08-23
 */
@Service
public class PostServiceImpl extends CrudServiceImpl<PostMapper, Post> implements IPostService{
		
}