package org.liezi.modules.system.service.impl;

import org.apache.commons.lang.StringUtils;
import org.liezi.modules.system.dao.MenuMapper;
import org.liezi.modules.system.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: lake.lei
 * @date: 2019/3/5 19:43
 * @description: Shiro Service实现类
 */
@Service
public class ShiroServiceImpl implements IShiroService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public Set<String> getUserPermissions(String ad) {
        List<String> permsList = new ArrayList<>();
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }
}