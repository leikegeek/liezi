package org.liezi.modules.system.service;

import org.liezi.modules.system.entity.ScheduleJob;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-01
 * @description:定时任务 Service层
 */
public interface IScheduleJobService extends IService<ScheduleJob> {
    /**
     * 保存定时任务
     * @param scheduleJob 定时任务类
     * @author: lake.lei
     * @date: 2019/3/1 11:36
     * @return: void
     */
    boolean saveJob(ScheduleJob scheduleJob);
    /**
     * 更新定时任务
     * @param scheduleJob 定时任务类
     * @author: lake.lei
     * @date: 2019/3/1 11:36
     * @return: void
     */
    boolean updateJob(ScheduleJob scheduleJob);
    /**
     * 批量删除定时任务
     * @param jobIds 任务ID
     * @author: lake.lei
     * @date: 2019/3/1 11:36
     * @return: void
     */
    void deleteBatchJob(Long[] jobIds);
    /**
     * 批量更新定时任务状态
     * @param jobIds 任务ID
     * @author: lake.lei
     * @date: 2019/3/1 11:36
     * @return: void
     */
    int updateBatchJob(Long[] jobIds, int status);

    /**
     * 立即执行任务
     * @param jobIds 任务ID
     * @author: lake.lei
     * @date: 2019/3/1 11:36
     * @return: void
     */
    void run(Long[] jobIds);
    /**
     * 暂停任务
     * @param jobIds 任务ID
     * @author: lake.lei
     * @date: 2019/3/1 11:37
     * @return: void
     */
    void pause(Long[] jobIds);
    /**
     * 恢复运行
     * @param jobIds 任务ID
     * @author: lake.lei
     * @date: 2019/3/1 11:37
     * @return: void
     */
    void resume(Long[] jobIds);

}
