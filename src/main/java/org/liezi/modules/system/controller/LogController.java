package org.liezi.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.liezi.base.ResultObject;
import org.liezi.base.ReturnEntity;
import org.liezi.common.utils.StringUtils;
import org.liezi.common.validator.ValidatorAddGroup;
import org.liezi.common.validator.ValidatorPageGroup;
import org.liezi.common.validator.ValidatorUpdateGroup;
import org.liezi.modules.common.service.IGeneratorIDService;
import org.liezi.modules.system.entity.Log;
import org.liezi.modules.system.service.ILogService;
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
 * @date: 2019-03-18
 * @description:系统日志控制类
 */
@Controller
@RequestMapping("/api/log")
@Api(description = "系统日志")
public class LogController {

  @Autowired
  private ILogService logService;
  @Autowired
  private IGeneratorIDService generatorIDService;

    @ApiOperation(value = "新增系统日志", notes = "新增系统日志")
    @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query",name = "username", value = "用户名", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "operation", value = "用户操作", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "method", value = "请求方法", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "params", value = "请求参数", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "time", value = "执行时长(毫秒)", dataType ="Long"),
      @ApiImplicitParam(paramType = "query",name = "returnMessage", value = "返回信息", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "requestStatus", value = "请求状态 1 成功 0 失败", dataType ="Integer"),
      @ApiImplicitParam(paramType = "query",name = "ip", value = "IP地址", dataType ="String"),
    })
    @PostMapping("/add")
    @ResponseBody
    public ReturnEntity add(@RequestBody @Validated(value= ValidatorAddGroup.class) Log log, BindingResult result){
        boolean addFlag ;
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
               return ResultObject.warning(error.getDefaultMessage(),null);
            }
        }
        log.setLogId(generatorIDService.generatorLongID());
        addFlag = logService.save(log);
        if(addFlag){
             return ResultObject.success(ReturnEntity.ADD_SUCCESS_MSG,log);
        }else{
             return ResultObject.error(ReturnEntity.ADD_FAIL_MSG,null);
        }

    }

    @ApiOperation(value = "更新系统日志", notes = "更新系统日志")
    @PostMapping("/update")
    @ResponseBody
    public ReturnEntity update(@RequestBody @Validated(value= ValidatorUpdateGroup.class) Log log, BindingResult result){
        boolean updateFlag;
        if(result.hasErrors()){
           for (ObjectError error : result.getAllErrors()) {
              return ResultObject.warning(error.getDefaultMessage(),null);
           }
        }
        updateFlag = logService.updateById(log);
        if(updateFlag){
            return ResultObject.success(ReturnEntity.UPDATE_SUCCESS_MSG,log);
        }else{
            return ResultObject.error(ReturnEntity.UPDATE_FAIL_MSG,null);
        }
    }

    @ApiOperation(value = "分页查询系统日志", notes = "分页查询系统日志")
    @PostMapping("/find")
    @ResponseBody
    public ReturnEntity find(@RequestBody @Validated(value= ValidatorPageGroup.class) Log log, BindingResult result){
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                return ResultObject.warning(error.getDefaultMessage(),null);
            }
        }
        IPage<Log> logList=logService.page(
            new Page<Log>(log.getCurrent(), log.getSize()),
            new QueryWrapper<Log>()
            .orderByDesc(true, StringUtils.entityFieldToDB(log.getDescs()))
            .orderByAsc(true,StringUtils.entityFieldToDB(log.getAscs()))
        );
        return ResultObject.success("success",logList.getRecords());
    }

    @ApiOperation(value = "查询系统日志列表", notes = "查询系统日志列表")
    @PostMapping("/list")
    @ResponseBody
    public ReturnEntity findList(@RequestBody Log log){
        List<Log> logList ;
        logList = logService.list(new QueryWrapper<Log>()
            .orderByDesc(true,StringUtils.entityFieldToDB(log.getDescs()))
            .orderByAsc(true,StringUtils.entityFieldToDB(log.getAscs()))
        );
        return ResultObject.success("success",logList);
    }

    @ApiOperation(value = "删除系统日志", notes = "删除系统日志")
    @PostMapping("/delete")
    @ResponseBody
    public ReturnEntity delete(@RequestBody String id){
        boolean deleteFlag = logService.removeById(id);
        if(deleteFlag){
            return ResultObject.success(ReturnEntity.DELETE_SUCCESS_MSG,null);
        }else{
            return ResultObject.error(ReturnEntity.DELETE_FAIL_MSG,null);
        }
    }

}

