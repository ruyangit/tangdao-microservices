/**
 *
 */
package com.tangdao.openfeign.system.model;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */

@Data
@Accessors(chain = true)
public class LoginAuthUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7848186533505718243L;

	private String userCode;

	private String username;

	private String password;

	private boolean enabled;

}
