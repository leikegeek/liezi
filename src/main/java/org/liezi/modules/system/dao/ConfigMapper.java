package org.liezi.modules.system.dao;

import org.liezi.modules.system.entity.Config;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:系统配置信息表 Mapper接口
 */
@Component(value = "configMapper")
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

}
