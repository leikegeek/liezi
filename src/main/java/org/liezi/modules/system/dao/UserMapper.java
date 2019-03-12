package org.liezi.modules.system.dao;

import org.liezi.modules.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-13
 * @description:系统用户 Mapper接口
 */
@Component(value = "userMapper")
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
