package com.tangdao.system.config;

public final class SysRedisConstant {

	/**
	 * 系统缓存命名空间
	 */
	public static String DEFAULT_NAMESPACE = "tangdao:";
	
	
	 /**
     * 系统常量配置数据数据
     */
    public static final String RED_SYS_CONFIG_LIST = DEFAULT_NAMESPACE + "sys_cache:config_list";
    
    /**
     * 系统字典数据数据
     */
    public static final String RED_SYS_DICT_DATA_LIST = DEFAULT_NAMESPACE + "sys_cache:dict_data_list";
    
    /**
     * 菜单地址列表
     */
    public static final String RED_MENU_NAME_PATH = DEFAULT_NAMESPACE + "sys_cache:menu_name_path";
}
