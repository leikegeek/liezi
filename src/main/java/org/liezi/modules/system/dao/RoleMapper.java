package org.liezi.modules.system.dao;

import org.liezi.modules.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-13
 * @description:角色 Mapper接口
 */
@Component(value = "roleMapper")
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
