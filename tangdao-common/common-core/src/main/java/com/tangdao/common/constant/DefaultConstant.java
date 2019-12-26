package com.tangdao.common.constant;

/** 
 * @ClassName: DefaultConstant.java 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author ruyang
 * @date 2019年2月18日 下午1:54:13
 * 
 */
public interface DefaultConstant {
	/**
	 *开发
	 */
	public static final String DEV_PROFILE = "dev";
	
	/**
	 * 测试
	 */
	public static final String TEST_PROFILE = "test";
	
	/**
	 * 生产
	 */
	public static final String PROD_PROFILE = "prod";
	
	/**
	 * 正常
	 */
	public static final String STATUS_NORMAL = "0";
	/**
	 * 已删除
	 */
	public static final String STATUS_DELETE = "1";
	/**
	 * 停用
	 */
	public static final String STATUS_DISABLE = "2";
	/**
	 * 冻结
	 */
	public static final String STATUS_FREEZE = "3";
	/**
	 * 审核
	 */
	public static final String STATUS_AUDIT = "4";
	/**
	 * 回退
	 */
	public static final String STATUS_AUDIT_BACK = "5";
	/**
	 * 草稿
	 */
	public static final String STATUS_DRAFT = "9";
}
