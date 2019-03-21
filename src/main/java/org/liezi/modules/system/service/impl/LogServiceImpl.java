package org.liezi.modules.system.service.impl;

import org.liezi.modules.system.entity.Log;
import org.liezi.modules.system.dao.LogMapper;
import org.liezi.modules.system.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:系统日志Servcie 实现类
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
