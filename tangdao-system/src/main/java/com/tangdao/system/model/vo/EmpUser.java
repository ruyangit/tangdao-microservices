/**
 *
 */
package com.tangdao.system.model.vo;

import com.tangdao.system.model.domain.Employee;
import com.tangdao.system.model.domain.User;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */
@Getter
@Setter
public class EmpUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Employee employee;
	
	public Employee getEmployee() {
		if(employee==null) {
			employee = new Employee();
		}
		return employee;
	}
}
