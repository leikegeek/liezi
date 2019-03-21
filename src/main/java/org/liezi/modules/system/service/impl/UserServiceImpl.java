package org.liezi.modules.system.service.impl;

import org.liezi.modules.system.entity.User;
import org.liezi.modules.system.dao.UserMapper;
import org.liezi.modules.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:系统用户Servcie 实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
