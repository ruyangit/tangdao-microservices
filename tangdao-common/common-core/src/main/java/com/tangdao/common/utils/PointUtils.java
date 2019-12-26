/**
 *
 */
package com.tangdao.common.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 配合日志组件进行埋点
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */
@Slf4j
public class PointUtils {

	public static final String SPLIT = "|";

	private PointUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * 	格式为：{时间}|{来源}|{对象id}|{类型}|{对象属性(以&分割)} 
	 *	示例： 2016-07-27 23:37:23|user-center|admin|user-login|ip=xxx.xxx.xx&userName=张三&userType=后台管理员
	 *
	 * @param id      对象id
	 * @param type    类型
	 * @param message 对象属性
	 */
	public static void info(String id, String type, String message) {
		log.info(id + SPLIT + type + SPLIT + message);
	}

	public static void debug(String id, String type, String message) {
		log.debug(id + SPLIT + type + SPLIT + message);
	}
}
