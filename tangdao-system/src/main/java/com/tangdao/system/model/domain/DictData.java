package com.tangdao.system.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangdao.common.model.DataEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict_data")
public class DictData extends DataEntity<DictData> {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典编码
	 */
	private String id;

	/**
	 * 字典标签
	 */
	private String dictLabel;

	/**
	 * 字典键值
	 */
	private String dictValue;

	/**
	 * 字典类型
	 */
	private String dictType;

	/**
	 * 本级排序号（升序）
	 */
	private Long dictSort;

	/**
	 * 字典描述
	 */
	private String description;

	/**
	 * css样式（如：color:red)
	 */
	private String cssStyle;

	/**
	 * css类名（如：red）
	 */
	private String cssClass;

	public interface SimpleView {
	}

}
