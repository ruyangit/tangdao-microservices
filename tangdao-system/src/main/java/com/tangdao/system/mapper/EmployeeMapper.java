package com.tangdao.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangdao.common.model.Page;
import com.tangdao.system.model.domain.Employee;
import com.tangdao.system.model.vo.EmpPost;
import com.tangdao.system.model.vo.EmpUser;

/**
 * 员工Mapper接口
 * 
 * @author ruyang
 * @version 2019-12-16
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

	public Page<EmpUser> findEmpUserPage(Page<Employee> page, EmpUser empUser);

	public EmpUser getEmpUserByUserCode(String userCode);

	public List<EmpPost> findEmpPost(String empCode);

	public void deleteEmpPost(String empCode);

	public int insertEmpPost(@Param("empCode") String empCode, @Param("postCodes") String[] postCodes);

}