package com.tangdao.system.service;

import java.util.List;

import com.tangdao.common.model.Page;
import com.tangdao.common.service.ICrudService;
import com.tangdao.system.model.domain.Employee;
import com.tangdao.system.model.vo.EmpPost;
import com.tangdao.system.model.vo.EmpUser;

/**
 * 员工Service
 * @author ruyang
 * @version 2019-12-16
 */
public interface IEmployeeService extends ICrudService<Employee> {
		
	/**
	 * 员工分页列表
	 * @param page
	 * @param empUser
	 * @return
	 */
	public Page<EmpUser> findEmpUserPage(Page<Employee> page, EmpUser empUser);
	
	/**
	 *  员工信息
	 * @param userCode
	 * @return
	 */
	public EmpUser getEmpUserByUserCode(String userCode, boolean isNewRecord);
	
	/**
	 *  获取员工所有岗位
	 * @param empCode
	 * @return
	 */
	public List<EmpPost> findEmpPost(String empCode);
	
	/**
	 * 保存员工
	 * @param empUser
	 */
	public void saveEmpUser(EmpUser empUser);
	
	/**
	 *  更新用户状态
	 * @param user
	 */
	public void updateStatus(EmpUser user);
	
	/**
	 *  删除用户
	 * @param user
	 */
	public void delete(EmpUser user);

}