package org.liezi.modules.system.service;

import java.util.Set;

/**
 * @author: lake.lei
 * @date: 2019/3/5 19:42
 * @description: Shiro Service层
 */
public interface IShiroService {
    /**
     * 根据用户获取授权列表
     * @author: lake.lei
     * @date: 2019/3/5 19:46
     * @return: java.util.List<java.lang.String>
     */
    Set<String> getUserPermissions(String token);
}
