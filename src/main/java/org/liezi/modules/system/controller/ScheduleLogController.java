package org.liezi.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.liezi.base.ResultObject;
import org.liezi.base.ReturnEntity;
import org.liezi.common.utils.StringUtils;
import org.liezi.common.validator.ValidatorPageGroup;
import org.liezi.common.validator.ValidatorUpdateGroup;
import org.liezi.modules.common.service.IGeneratorIDService;
import org.liezi.modules.system.entity.ScheduleLog;
import org.liezi.modules.system.service.IScheduleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @author: lake.lei
 * @date: 2019-03-01
 * @description:定时任务日志控制类
 */
@Controller
@RequestMapping("/api/scheduleLog")
@Api(description = "定时任务日志")
public class ScheduleLogController {

  @Autowired
  private IScheduleLogService scheduleLogService;
  @Autowired
  private IGeneratorIDService generatorIDService;


    @ApiOperation(value = "更新定时任务日志", notes = "更新定时任务日志")
    @PostMapping("/update")
    @ResponseBody
    public ReturnEntity update(@RequestBody @Validated(value= ValidatorUpdateGroup.class) ScheduleLog scheduleLog, BindingResult result){
        boolean updateFlag;
        if(result.hasErrors()){
           for (ObjectError error : result.getAllErrors()) {
              return ResultObject.warning(error.getDefaultMessage(),null);
           }
        }
        updateFlag = scheduleLogService.updateById(scheduleLog);
        if(updateFlag){
            return ResultObject.success(ReturnEntity.UPDATE_SUCCESS_MSG,scheduleLog);
        }else{
            return ResultObject.error(ReturnEntity.UPDATE_FAIL_MSG,null);
        }
    }

    @ApiOperation(value = "分页查询定时任务日志", notes = "分页查询定时任务日志")
    @PostMapping("/find")
    @ResponseBody
    public ReturnEntity find(@RequestBody @Validated(value= ValidatorPageGroup.class) ScheduleLog scheduleLog, BindingResult result){
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                return ResultObject.warning(error.getDefaultMessage(),null);
            }
        }
        IPage<ScheduleLog> scheduleLogList=scheduleLogService.page(
            new Page<ScheduleLog>(scheduleLog.getCurrent(), scheduleLog.getSize()),
            new QueryWrapper<ScheduleLog>()
            .orderByDesc(true, StringUtils.entityFieldToDB(scheduleLog.getDescs()))
            .orderByAsc(true,StringUtils.entityFieldToDB(scheduleLog.getAscs()))
        );
        return ResultObject.success("success",scheduleLogList.getRecords());
    }

    @ApiOperation(value = "查询定时任务日志列表", notes = "查询定时任务日志列表")
    @PostMapping("/list")
    @ResponseBody
    public ReturnEntity findList(@RequestBody ScheduleLog scheduleLog){
        List<ScheduleLog> scheduleLogList ;
        scheduleLogList = scheduleLogService.list(new QueryWrapper<ScheduleLog>()
            .orderByDesc(true,StringUtils.entityFieldToDB(scheduleLog.getDescs()))
            .orderByAsc(true,StringUtils.entityFieldToDB(scheduleLog.getAscs()))
        );
        return ResultObject.success("success",scheduleLogList);
    }

    @ApiOperation(value = "删除定时任务日志", notes = "删除定时任务日志")
    @PostMapping("/delete")
    @ResponseBody
    public ReturnEntity delete(@RequestBody String id){
        boolean deleteFlag = scheduleLogService.removeById(id);
        if(deleteFlag){
            return ResultObject.success(ReturnEntity.DELETE_SUCCESS_MSG,null);
        }else{
            return ResultObject.error(ReturnEntity.DELETE_FAIL_MSG,null);
        }
    }

}

