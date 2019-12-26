/**
 *
 */
package com.tangdao.system.config;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.tangdao.mybatis.handler.AbstractMetaObjectHandler;

/**
 * 統一字段填充
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */
@Component
public class MybatisMetaObjectHandler extends AbstractMetaObjectHandler {

	@Override
	protected void insertFillWithUser(MetaObject metaObject) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void updateFillWithUser(MetaObject metaObject) {
		// TODO Auto-generated method stub
		
	}
}
