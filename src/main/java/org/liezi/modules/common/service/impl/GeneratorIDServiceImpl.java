package org.liezi.modules.common.service.impl;

import org.liezi.common.utils.IdWorker;
import org.liezi.modules.common.service.IGeneratorIDService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author: lake.lei
 * @date: 2018/8/5 15:12
 * @description:分布式ID生成器
 */
@Service("generatorIDService")
public class GeneratorIDServiceImpl implements IGeneratorIDService {
    /**
     * 用于生成ID
     */
    @Value("${system-config.worker-id:0}")
    private Integer workerID;
    /**
     * 用于生成ID
     */
    @Value("${system-config.datacenter-id:0}")
    private Integer datacenterID;

    private IdWorker idWorker;

    @Override
    public Long generatorLongID() {
        if(null == idWorker){
            idWorker = new IdWorker(workerID,datacenterID);
        }
        return idWorker.nextId();
    }

    @Override
    public String generatorStringID() {
        if(null == idWorker){
            idWorker = new IdWorker(workerID,datacenterID);
        }
        return String.valueOf(idWorker.nextId());
    }
}