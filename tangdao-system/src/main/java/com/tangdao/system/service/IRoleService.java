package com.tangdao.system.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangdao.common.service.ICrudService;
import com.tangdao.system.model.domain.Role;
import com.tangdao.system.model.domain.User;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
public interface IRoleService extends ICrudService<Role> {

	public List<Role> findByUserCode(String userCode);

	public boolean checkRoleNameExists(String oldRoleName, String roleName);

	public void insertRoleMenu(Role role, String[] menuCodes);

	public IPage<User> findUserPage(IPage<Role> page, Role role);

	public int deleteRoleUser(String roleCode, String userCode);
	
	public int insertRoleUser(String roleCode, String[] userCodes);

}
