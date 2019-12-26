package com.tangdao.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangdao.common.lang.StringUtils;
import com.tangdao.common.model.Page;
import com.tangdao.common.service.impl.CrudServiceImpl;
import com.tangdao.system.mapper.EmployeeMapper;
import com.tangdao.system.model.domain.Employee;
import com.tangdao.system.model.domain.User;
import com.tangdao.system.model.vo.EmpPost;
import com.tangdao.system.model.vo.EmpUser;
import com.tangdao.system.service.IEmployeeService;
import com.tangdao.system.service.IUserService;

/**
 * 员工ServiceImpl
 * @author ruyang
 * @version 2019-12-16
 */
@Service
public class EmployeeServiceImpl extends CrudServiceImpl<EmployeeMapper, Employee> implements IEmployeeService{
	
	@Autowired
	private IUserService userService;
		
	@Override
	public Page<EmpUser> findEmpUserPage(Page<Employee> page, EmpUser empUser) {
		return super.baseMapper.findEmpUserPage(page, empUser);
	}

	@Override
	public EmpUser getEmpUserByUserCode(String userCode, boolean isNewRecord) {
		if (isNewRecord || userCode == null || StringUtils.isEmpty(userCode)) {
			return new EmpUser();
		}
		return this.baseMapper.getEmpUserByUserCode(userCode);
	}
	
	@Override
	public void saveEmpUser(EmpUser user) {
		if(user.getIsNewRecord()) {
			user.setUserType(User.USER_TYPE_EMPLOYEE);
			user.setMgrType(User.MGR_TYPE_NOT_ADMIN);
		}
		//保存用户
		this.userService.saveOrUpdate(user);
		Employee employee = user.getEmployee();
		boolean isNewRecord = employee.getIsNewRecord();
		if(StringUtils.isEmpty(employee.getEmpCode())) {
			employee.setEmpCode(user.getUserCode());
		}
		if(StringUtils.isEmpty(employee.getEmpName())) {
			employee.setEmpName(user.getUsername());
		}
		employee.setIsNewRecord(isNewRecord);
		//保存员工
		this.saveOrUpdate(employee);
		// 岗位关联
		this.baseMapper.deleteEmpPost(employee.getEmpCode());
		if(StringUtils.isNotBlank(employee.getPostCodes())) {
			this.baseMapper.insertEmpPost(employee.getEmpCode(), employee.getPostCodes().split(","));
		}
	}
	
	@Override
	public void updateStatus(EmpUser user) {
		this.userService.updateStatusById(user);
		Employee employee = user.getEmployee();
		employee.setStatus(user.getStatus());
		this.updateStatusById(employee);
	}
	
	@Override
	public void delete(EmpUser user) {
		this.userService.deleteById(user.getUserCode());
		this.deleteById(user.getEmployee().getEmpCode());
	}

	@Override
	public List<EmpPost> findEmpPost(String empCode) {
		return this.baseMapper.findEmpPost(empCode);
	}
}