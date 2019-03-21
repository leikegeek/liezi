package org.liezi.modules.system.service.impl;

import org.liezi.modules.system.entity.Menu;
import org.liezi.modules.system.dao.MenuMapper;
import org.liezi.modules.system.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:菜单管理Servcie 实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
