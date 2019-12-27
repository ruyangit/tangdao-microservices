package com.tangdao.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangdao.system.model.domain.Role;
import com.tangdao.system.model.domain.User;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	public IPage<User> findUserPage(IPage<Role> page, Role role);

	public List<Role> findByUserCode(Role role);

	public int deleteRoleMenu(String roleCode);

	public int deleteRoleUser(@Param("roleCode") String roleCode, @Param("userCode") String userCode);

	public int insertRoleUser(@Param("roleCode") String roleCode, @Param("userCode") String userCode);

	public int insertRoleMenu(@Param("roleCode") String roleCode, @Param("menuCodes") String[] menuCodes);
}
