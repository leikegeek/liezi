package org.liezi.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liezi.modules.system.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:系统用户 Mapper接口
 */
@Component(value = "userMapper")
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(String userId);

}
