package org.liezi.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.liezi.base.ResultObject;
import org.liezi.base.ReturnEntity;
import org.liezi.common.utils.StringUtils;
import org.liezi.common.validator.ValidatorAddGroup;
import org.liezi.common.validator.ValidatorPageGroup;
import org.liezi.common.validator.ValidatorUpdateGroup;
import org.liezi.modules.common.service.IGeneratorIDService;
import org.liezi.modules.system.entity.ScheduleJob;
import org.liezi.modules.system.service.IScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * @description:定时任务控制类
 */
@Controller
@RequestMapping("/api/scheduleJob")
@Api(description = "定时任务")
public class ScheduleJobController {

  @Autowired
  private IScheduleJobService scheduleJobService;
  @Autowired
  private IGeneratorIDService generatorIDService;

    @ApiOperation(value = "新增定时任务", notes = "新增定时任务")
    @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query",name = "id", value = "任务id", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "beanName", value = "spring bean名称", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "methodName", value = "方法名", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "params", value = "参数", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "cronExpression", value = "cron表达式", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "status", value = "任务状态  0：正常  1：暂停", dataType ="Integer"),
      @ApiImplicitParam(paramType = "query",name = "remark", value = "备注", dataType ="String"),
    })
    @PostMapping("/add")
    @ResponseBody
    public ReturnEntity add(@RequestBody @Validated(value= ValidatorAddGroup.class) ScheduleJob scheduleJob, BindingResult result){
        boolean addFlag ;
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
               return ResultObject.warning(error.getDefaultMessage(),null);
            }
        }
        scheduleJob.setId(generatorIDService.generatorStringID());
        addFlag = scheduleJobService.saveJob(scheduleJob);
        if(addFlag){
             return ResultObject.success(ReturnEntity.ADD_SUCCESS_MSG,scheduleJob);
        }else{
             return ResultObject.error(ReturnEntity.ADD_FAIL_MSG,null);
        }

    }

    @ApiOperation(value = "更新定时任务", notes = "更新定时任务")
    @PostMapping("/update")
    @ResponseBody
    public ReturnEntity update(@RequestBody @Validated(value= ValidatorUpdateGroup.class) ScheduleJob scheduleJob, BindingResult result){
        boolean updateFlag;
        if(result.hasErrors()){
           for (ObjectError error : result.getAllErrors()) {
              return ResultObject.warning(error.getDefaultMessage(),null);
           }
        }
        updateFlag = scheduleJobService.updateJob(scheduleJob);
        if(updateFlag){
            return ResultObject.success(ReturnEntity.UPDATE_SUCCESS_MSG,scheduleJob);
        }else{
            return ResultObject.error(ReturnEntity.UPDATE_FAIL_MSG,null);
        }
    }

    @ApiOperation(value = "分页查询定时任务", notes = "分页查询定时任务")
    @PostMapping("/find")
    @ResponseBody
    public ReturnEntity find(@RequestBody @Validated(value= ValidatorPageGroup.class) ScheduleJob scheduleJob, BindingResult result){
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                return ResultObject.warning(error.getDefaultMessage(),null);
            }
        }
        IPage<ScheduleJob> scheduleJobList=scheduleJobService.page(
            new Page<>(scheduleJob.getCurrent(), scheduleJob.getSize()),
            new QueryWrapper<ScheduleJob>()
            .orderByDesc(true, StringUtils.entityFieldToDB(scheduleJob.getDescs()))
            .orderByAsc(true,StringUtils.entityFieldToDB(scheduleJob.getAscs()))
        );
        return ResultObject.success("success",scheduleJobList.getRecords());
    }

    @ApiOperation(value = "查询定时任务列表", notes = "查询定时任务列表")
    @PostMapping("/list")
    @ResponseBody
    public ReturnEntity findList(@RequestBody ScheduleJob scheduleJob){
        List<ScheduleJob> scheduleJobList ;
        scheduleJobList = scheduleJobService.list(new QueryWrapper<ScheduleJob>()
            .orderByDesc(true,StringUtils.entityFieldToDB(scheduleJob.getDescs()))
            .orderByAsc(true,StringUtils.entityFieldToDB(scheduleJob.getAscs()))
        );
        return ResultObject.success("success",scheduleJobList);
    }

    @ApiOperation(value = "删除定时任务", notes = "删除定时任务")
    @PostMapping("/delete")
    @ResponseBody
    public ReturnEntity delete(@RequestBody String id){
        boolean deleteFlag = scheduleJobService.removeById(id);
        if(deleteFlag){
            return ResultObject.success(ReturnEntity.DELETE_SUCCESS_MSG,null);
        }else{
            return ResultObject.error(ReturnEntity.DELETE_FAIL_MSG,null);
        }
    }

    /**
     * 立即执行任务
     */
    @PostMapping("/run")
    @ResponseBody
    public ReturnEntity run(@RequestBody String[] jobIds){
        scheduleJobService.run(jobIds);
        return ResultObject.success(ReturnEntity.OPERATION_SUCCESS,"执行成功");
    }

    /**
     * 暂停定时任务
     */
    @PostMapping("/pause")
    @ResponseBody
    public ReturnEntity pause(@RequestBody String[] jobIds){
        scheduleJobService.pause(jobIds);
        return ResultObject.success(ReturnEntity.OPERATION_SUCCESS,"暂停成功");
    }

    /**
     * 恢复定时任务
     */
    @PostMapping("/resume")
    @ResponseBody
    public ReturnEntity resume(@RequestBody String[] jobIds){
        scheduleJobService.resume(jobIds);
        return ResultObject.success(ReturnEntity.OPERATION_SUCCESS,"恢复成功");
    }

}

