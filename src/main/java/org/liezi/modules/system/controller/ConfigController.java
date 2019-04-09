package org.liezi.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.liezi.base.ResultObject;
import org.liezi.base.ReturnEntity;
import org.liezi.common.annotation.SuperFun;
import org.liezi.common.utils.StringUtils;
import org.liezi.common.validator.ValidatorAddGroup;
import org.liezi.common.validator.ValidatorPageGroup;
import org.liezi.common.validator.ValidatorUpdateGroup;
import org.liezi.modules.common.service.IGeneratorIDService;
import org.liezi.modules.system.entity.Config;
import org.liezi.modules.system.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:系统配置信息表控制类
 */
@Controller
@RequestMapping("/api/config")
@Api(description = "系统配置信息表")
public class ConfigController {

  @Autowired
  private IConfigService configService;
  @Autowired
  private IGeneratorIDService generatorIDService;

  @ApiOperation(value = "新增系统配置信息表", notes = "新增系统配置信息表")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query",name = "paramKey", value = "key", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "paramValue", value = "value", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "status", value = "状态   0：隐藏   1：显示", dataType ="Integer"),
      @ApiImplicitParam(paramType = "query",name = "remark", value = "备注", dataType ="String"),
    })
  @SuperFun(logFun = "保存系统配置信息表",validationFun = true)
  @RequiresPermissions("sys:config:add")
  @PostMapping("/add")
  @ResponseBody
  public ReturnEntity add(@RequestBody @Validated(value= ValidatorAddGroup.class) Config config, BindingResult result){
      boolean addFlag ;
      config.setConfigId(generatorIDService.generatorStringID());
      addFlag = configService.save(config);
      if(addFlag){
          return ResultObject.success(ReturnEntity.ADD_SUCCESS_MSG,config);
      }else{
          return ResultObject.error(ReturnEntity.ADD_FAIL_MSG,null);
      }

  }

  @ApiOperation(value = "更新系统配置信息表", notes = "更新系统配置信息表")
  @SuperFun(logFun = "更新系统配置信息表",validationFun = true)
  @RequiresPermissions("sys:config:update")
  @PostMapping("/update")
  @ResponseBody
  public ReturnEntity update(@RequestBody @Validated(value= ValidatorUpdateGroup.class) Config config, BindingResult result){
      boolean updateFlag;
      updateFlag = configService.updateById(config);
      if(updateFlag){
          return ResultObject.success(ReturnEntity.UPDATE_SUCCESS_MSG,config);
      }else{
          return ResultObject.error(ReturnEntity.UPDATE_FAIL_MSG,null);
      }
  }

  @ApiOperation(value = "分页查询系统配置信息表", notes = "分页查询系统配置信息表")
  @SuperFun(validationFun = true)
  @RequiresPermissions("sys:config:page")
  @PostMapping("/find")
  @ResponseBody
  public ReturnEntity find(@RequestBody @Validated(value= ValidatorPageGroup.class) Config config, BindingResult result){
      IPage<Config> configList=configService.page(
          new Page<>(config.getCurrent(), config.getSize()),
          new QueryWrapper<Config>()
          .orderByDesc(true, StringUtils.entityFieldToDB(config.getDescs()))
          .orderByAsc(true,StringUtils.entityFieldToDB(config.getAscs())));
      return ResultObject.success("success",configList.getRecords());
  }

  @ApiOperation(value = "查询系统配置信息表列表", notes = "查询系统配置信息表列表")
  @RequiresPermissions("sys:config:list")
  @PostMapping("/list")
  @ResponseBody
  public ReturnEntity findList(@RequestBody Config config){
      List<Config> configList ;
      configList = configService.list(new QueryWrapper<Config>()
          .orderByDesc(true,StringUtils.entityFieldToDB(config.getDescs()))
          .orderByAsc(true,StringUtils.entityFieldToDB(config.getAscs())));
      return ResultObject.success("success",configList);
  }

  @ApiOperation(value = "删除系统配置信息表", notes = "删除系统配置信息表")
  @SuperFun(logFun = "删除系统配置信息表",validationFun = true)
  @RequiresPermissions("sys:config:delete")
  @PostMapping("/delete")
  @ResponseBody
  public ReturnEntity delete(@RequestBody String id){
      boolean deleteFlag = configService.removeById(id);
      if(deleteFlag){
          return ResultObject.success(ReturnEntity.DELETE_SUCCESS_MSG,null);
      }else{
          return ResultObject.error(ReturnEntity.DELETE_FAIL_MSG,null);
      }
  }

}

