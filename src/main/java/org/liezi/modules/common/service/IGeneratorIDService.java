package org.liezi.modules.common.service;

/**
 *
 * @author: lake.lei
 * @date: 2018/8/5 15:11
 * @description:
 */
public interface IGeneratorIDService {
    /**
     * 获取long类型的ID
     * @author: lake.lei
     * @date: 2018/8/5 18:47
     * @param: []
     * @return: java.lang.Long
     */
    Long generatorLongID();
    /**
     * 获取String类型的ID
     * @author: lake.lei
     * @date: 2018/8/5 18:47
     * @param: []
     * @return: java.lang.String
     */
    String generatorStringID();
}
