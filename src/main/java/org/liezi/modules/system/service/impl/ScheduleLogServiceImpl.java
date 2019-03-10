package org.liezi.modules.system.service.impl;

import org.liezi.modules.system.entity.ScheduleLog;
import org.liezi.modules.system.dao.ScheduleLogMapper;
import org.liezi.modules.system.service.IScheduleLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-01
 * @description:定时任务日志Servcie 实现类
 */
@Service("scheduleLogService")
public class ScheduleLogServiceImpl extends ServiceImpl<ScheduleLogMapper, ScheduleLog> implements IScheduleLogService {

}
