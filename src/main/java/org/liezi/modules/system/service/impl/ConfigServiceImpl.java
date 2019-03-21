package org.liezi.modules.system.service.impl;

import org.liezi.modules.system.entity.Config;
import org.liezi.modules.system.dao.ConfigMapper;
import org.liezi.modules.system.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:系统配置信息表Servcie 实现类
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
