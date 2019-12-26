package com.tangdao.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangdao.system.model.domain.Menu;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author ruyang
 * @since 2019-07-02
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

	public List<Menu> findByRoleMenu(Menu menu);

	public List<Menu> findByUserMenu(Menu menu);
}
