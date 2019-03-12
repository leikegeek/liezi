package org.liezi.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liezi.modules.system.dao.ScheduleJobMapper;
import org.liezi.modules.system.entity.ScheduleJob;
import org.liezi.modules.system.service.IScheduleJobService;
import org.liezi.quartz.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-01
 * @description:定时任务Servcie 实现类
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements IScheduleJobService {
    @Qualifier("schedulerFactoryBean")
    @Autowired
    private Scheduler scheduler;
    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init(){
        List<ScheduleJob> scheduleJobList = this.list(null);
        for(ScheduleJob scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveJob(ScheduleJob scheduleJob) {
        this.save(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateJob(ScheduleJob scheduleJob) {
        this.updateById(scheduleJob);
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchJob(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        //删除数据
        this.removeByIds(Arrays.asList(jobIds));
    }
    /**
     * TODO 后续处理
     * @param jobIds
     * @param status
     * @author: lake.lei
     * @date: 2019/3/1 12:05
     * @return: int
     */
    @Override
    public int updateBatchJob(Long[] jobIds, int status) {
        return 0;
    }

    @Override
    public void run(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.run(scheduler, this.getById(jobId));
        }
    }

    @Override
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler,jobId);
        }
    }

    @Override
    public void resume(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler,jobId);
        }
    }
}
