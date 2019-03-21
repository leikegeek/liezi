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
import org.liezi.common.annotation.SuperFun;
import org.liezi.common.utils.StringUtils;
import org.liezi.common.validator.ValidatorAddGroup;
import org.liezi.common.validator.ValidatorPageGroup;
import org.liezi.common.validator.ValidatorUpdateGroup;
import org.liezi.modules.common.service.IGeneratorIDService;
import org.liezi.modules.system.entity.User;
import org.liezi.modules.system.service.IUserService;
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
 * @description:系统用户控制类
 */
@Controller
@RequestMapping("/api/user")
@Api(description = "系统用户")
public class UserController {

  @Autowired
  private IUserService userService;
  @Autowired
  private IGeneratorIDService generatorIDService;

  @ApiOperation(value = "新增系统用户", notes = "新增系统用户")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query",name = "username", value = "用户名", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "password", value = "密码", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "salt", value = "盐", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "email", value = "邮箱", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "mobile", value = "手机号", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "status", value = "状态  0：禁用   1：正常", dataType ="Integer"),
    })
  @SuperFun(logFun = "保存系统用户",validationFun = true)
  @PostMapping("/add")
  @ResponseBody
  public ReturnEntity add(@RequestBody @Validated(value= ValidatorAddGroup.class) User user, BindingResult result){
      boolean addFlag ;
      user.setUserId(generatorIDService.generatorStringID());
      addFlag = userService.save(user);
      if(addFlag){
          return ResultObject.success(ReturnEntity.ADD_SUCCESS_MSG,user);
      }else{
          return ResultObject.error(ReturnEntity.ADD_FAIL_MSG,null);
      }

  }

  @ApiOperation(value = "更新系统用户", notes = "更新系统用户")
  @SuperFun(logFun = "更新系统用户",validationFun = true)
  @PostMapping("/update")
  @ResponseBody
  public ReturnEntity update(@RequestBody @Validated(value= ValidatorUpdateGroup.class) User user, BindingResult result){
      boolean updateFlag;
      updateFlag = userService.updateById(user);
      if(updateFlag){
          return ResultObject.success(ReturnEntity.UPDATE_SUCCESS_MSG,user);
      }else{
          return ResultObject.error(ReturnEntity.UPDATE_FAIL_MSG,null);
      }
  }

  @ApiOperation(value = "分页查询系统用户", notes = "分页查询系统用户")
  @SuperFun(validationFun = true)
  @PostMapping("/find")
  @ResponseBody
  public ReturnEntity find(@RequestBody @Validated(value= ValidatorPageGroup.class) User user, BindingResult result){
      IPage<User> userList=userService.page(
          new Page<User>(user.getCurrent(), user.getSize()),
          new QueryWrapper<User>()
          .orderByDesc(true, StringUtils.entityFieldToDB(user.getDescs()))
          .orderByAsc(true,StringUtils.entityFieldToDB(user.getAscs())));
      return ResultObject.success("success",userList.getRecords());
  }

  @ApiOperation(value = "查询系统用户列表", notes = "查询系统用户列表")
  @PostMapping("/list")
  @ResponseBody
  public ReturnEntity findList(@RequestBody User user){
      List<User> userList ;
      userList = userService.list(new QueryWrapper<User>()
          .orderByDesc(true,StringUtils.entityFieldToDB(user.getDescs()))
          .orderByAsc(true,StringUtils.entityFieldToDB(user.getAscs())));
      return ResultObject.success("success",userList);
  }

  @ApiOperation(value = "删除系统用户", notes = "删除系统用户")
  @SuperFun(logFun = "删除系统用户",validationFun = true)
  @PostMapping("/delete")
  @ResponseBody
  public ReturnEntity delete(@RequestBody String id){
      boolean deleteFlag = userService.removeById(id);
      if(deleteFlag){
          return ResultObject.success(ReturnEntity.DELETE_SUCCESS_MSG,null);
      }else{
          return ResultObject.error(ReturnEntity.DELETE_FAIL_MSG,null);
      }
  }

}

