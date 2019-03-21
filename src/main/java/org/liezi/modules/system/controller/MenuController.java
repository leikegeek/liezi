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
import org.liezi.modules.system.entity.Menu;
import org.liezi.modules.system.service.IMenuService;
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
 * @description:菜单管理控制类
 */
@Controller
@RequestMapping("/api/menu")
@Api(description = "菜单管理")
public class MenuController {

  @Autowired
  private IMenuService menuService;
  @Autowired
  private IGeneratorIDService generatorIDService;

  @ApiOperation(value = "新增菜单管理", notes = "新增菜单管理")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query",name = "parentId", value = "父菜单ID，一级菜单为0", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "name", value = "菜单名称", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "url", value = "菜单URL", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "perms", value = "授权(多个用逗号分隔，如：user:list,user:create)", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "type", value = "类型   0：目录   1：菜单   2：按钮", dataType ="Integer"),
      @ApiImplicitParam(paramType = "query",name = "icon", value = "菜单图标", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "orderNum", value = "排序", dataType ="Integer"),
    })
  @SuperFun(logFun = "保存菜单管理",validationFun = true)
  @PostMapping("/add")
  @ResponseBody
  public ReturnEntity add(@RequestBody @Validated(value= ValidatorAddGroup.class) Menu menu, BindingResult result){
      boolean addFlag ;
      menu.setMenuId(generatorIDService.generatorStringID());
      addFlag = menuService.save(menu);
      if(addFlag){
          return ResultObject.success(ReturnEntity.ADD_SUCCESS_MSG,menu);
      }else{
          return ResultObject.error(ReturnEntity.ADD_FAIL_MSG,null);
      }

  }

  @ApiOperation(value = "更新菜单管理", notes = "更新菜单管理")
  @SuperFun(logFun = "更新菜单管理",validationFun = true)
  @PostMapping("/update")
  @ResponseBody
  public ReturnEntity update(@RequestBody @Validated(value= ValidatorUpdateGroup.class) Menu menu, BindingResult result){
      boolean updateFlag;
      updateFlag = menuService.updateById(menu);
      if(updateFlag){
          return ResultObject.success(ReturnEntity.UPDATE_SUCCESS_MSG,menu);
      }else{
          return ResultObject.error(ReturnEntity.UPDATE_FAIL_MSG,null);
      }
  }

  @ApiOperation(value = "分页查询菜单管理", notes = "分页查询菜单管理")
  @SuperFun(validationFun = true)
  @PostMapping("/find")
  @ResponseBody
  public ReturnEntity find(@RequestBody @Validated(value= ValidatorPageGroup.class) Menu menu, BindingResult result){
      IPage<Menu> menuList=menuService.page(
          new Page<Menu>(menu.getCurrent(), menu.getSize()),
          new QueryWrapper<Menu>()
          .orderByDesc(true, StringUtils.entityFieldToDB(menu.getDescs()))
          .orderByAsc(true,StringUtils.entityFieldToDB(menu.getAscs())));
      return ResultObject.success("success",menuList.getRecords());
  }

  @ApiOperation(value = "查询菜单管理列表", notes = "查询菜单管理列表")
  @PostMapping("/list")
  @ResponseBody
  public ReturnEntity findList(@RequestBody Menu menu){
      List<Menu> menuList ;
      menuList = menuService.list(new QueryWrapper<Menu>()
          .orderByDesc(true,StringUtils.entityFieldToDB(menu.getDescs()))
          .orderByAsc(true,StringUtils.entityFieldToDB(menu.getAscs())));
      return ResultObject.success("success",menuList);
  }

  @ApiOperation(value = "删除菜单管理", notes = "删除菜单管理")
  @SuperFun(logFun = "删除菜单管理",validationFun = true)
  @PostMapping("/delete")
  @ResponseBody
  public ReturnEntity delete(@RequestBody String id){
      boolean deleteFlag = menuService.removeById(id);
      if(deleteFlag){
          return ResultObject.success(ReturnEntity.DELETE_SUCCESS_MSG,null);
      }else{
          return ResultObject.error(ReturnEntity.DELETE_FAIL_MSG,null);
      }
  }

}

