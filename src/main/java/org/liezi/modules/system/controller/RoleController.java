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
import org.liezi.modules.system.entity.Role;
import org.liezi.modules.system.service.IRoleService;
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
 * @date: 2019-03-13
 * @description:角色控制类
 */
@Controller
@RequestMapping("/api/role")
@Api(description = "角色")
public class RoleController {

  @Autowired
  private IRoleService roleService;
  @Autowired
  private IGeneratorIDService generatorIDService;

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query",name = "roleName", value = "角色名称", dataType ="String"),
      @ApiImplicitParam(paramType = "query",name = "remark", value = "备注", dataType ="String"),
    })
    @PostMapping("/add")
    @ResponseBody
    public ReturnEntity add(@RequestBody @Validated(value= ValidatorAddGroup.class) Role role, BindingResult result){
        boolean addFlag ;
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
               return ResultObject.warning(error.getDefaultMessage(),null);
            }
        }
        role.setRoleId(generatorIDService.generatorStringID());
        addFlag = roleService.save(role);
        if(addFlag){
             return ResultObject.success(ReturnEntity.ADD_SUCCESS_MSG,role);
        }else{
             return ResultObject.error(ReturnEntity.ADD_FAIL_MSG,null);
        }

    }

    @ApiOperation(value = "更新角色", notes = "更新角色")
    @PostMapping("/update")
    @ResponseBody
    public ReturnEntity update(@RequestBody @Validated(value= ValidatorUpdateGroup.class) Role role, BindingResult result){
        boolean updateFlag;
        if(result.hasErrors()){
           for (ObjectError error : result.getAllErrors()) {
              return ResultObject.warning(error.getDefaultMessage(),null);
           }
        }
        updateFlag = roleService.updateById(role);
        if(updateFlag){
            return ResultObject.success(ReturnEntity.UPDATE_SUCCESS_MSG,role);
        }else{
            return ResultObject.error(ReturnEntity.UPDATE_FAIL_MSG,null);
        }
    }

    @ApiOperation(value = "分页查询角色", notes = "分页查询角色")
    @PostMapping("/find")
    @ResponseBody
    public ReturnEntity find(@RequestBody @Validated(value= ValidatorPageGroup.class) Role role, BindingResult result){
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                return ResultObject.warning(error.getDefaultMessage(),null);
            }
        }
        IPage<Role> roleList=roleService.page(
            new Page<>(role.getCurrent(), role.getSize()),
            new QueryWrapper<Role>()
            .orderByDesc(true, StringUtils.entityFieldToDB(role.getDescs()))
            .orderByAsc(true,StringUtils.entityFieldToDB(role.getAscs()))
        );
        return ResultObject.success("success",roleList.getRecords());
    }

    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @PostMapping("/list")
    @ResponseBody
    public ReturnEntity findList(@RequestBody Role role){
        List<Role> roleList ;
        roleList = roleService.list(new QueryWrapper<Role>()
            .orderByDesc(true,StringUtils.entityFieldToDB(role.getDescs()))
            .orderByAsc(true,StringUtils.entityFieldToDB(role.getAscs()))
        );
        return ResultObject.success("success",roleList);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PostMapping("/delete")
    @ResponseBody
    public ReturnEntity delete(@RequestBody String id){
        boolean deleteFlag = roleService.removeById(id);
        if(deleteFlag){
            return ResultObject.success(ReturnEntity.DELETE_SUCCESS_MSG,null);
        }else{
            return ResultObject.error(ReturnEntity.DELETE_FAIL_MSG,null);
        }
    }

}

