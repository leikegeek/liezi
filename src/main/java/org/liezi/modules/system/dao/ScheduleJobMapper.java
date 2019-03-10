package org.liezi.modules.system.dao;

import org.liezi.modules.system.entity.ScheduleJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-01
 * @description:定时任务 Mapper接口
 */
@Component(value = "scheduleJobMapper")
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

}
