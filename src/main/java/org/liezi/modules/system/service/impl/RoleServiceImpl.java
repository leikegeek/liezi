package org.liezi.modules.system.service.impl;

import org.liezi.modules.system.entity.Role;
import org.liezi.modules.system.dao.RoleMapper;
import org.liezi.modules.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:角色Servcie 实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
