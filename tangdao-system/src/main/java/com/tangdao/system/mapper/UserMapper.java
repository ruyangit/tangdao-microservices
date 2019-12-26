package com.tangdao.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangdao.system.model.domain.User;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

	public void deleteUserRole(String userCode);

	public int insertUserRole(@Param("userCode") String userCode, @Param("roleCodes") String[] roleCodes);

}
