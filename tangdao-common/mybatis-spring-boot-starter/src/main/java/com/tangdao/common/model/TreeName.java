package com.tangdao.common.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName: TreeName.java 
 * @Description: TODO(树节点名称注解) 
 * @author ruyang
 * @date 2018年12月24日 上午11:27:58
 *  
 *  用于通过注解获取节点的名称，树结构的数据库操作中使用
 *  需强制按照规范使用
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TreeName {

	 /**
     * 字段值（驼峰命名方式,该值可无）
     */
    String value() default "";

    /**
     * 是否为数据库表字段
     * 默认 true 存在，false 不存在
     */
    boolean exist() default true;
}
