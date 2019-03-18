package org.liezi.modules.system.dao;

import org.liezi.modules.system.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-18
 * @description:系统日志 Mapper接口
 */
@Component(value = "logMapper")
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
