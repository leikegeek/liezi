package org.liezi.modules.system.dao;

import org.liezi.modules.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-05
 * @description:菜单管理 Mapper接口
 */
@Component(value = "menuMapper")
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
