package org.liezi.quartz;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.liezi.common.utils.SpringContextUtils;
import org.liezi.modules.common.service.IGeneratorIDService;
import org.liezi.modules.system.entity.ScheduleJob;
import org.liezi.modules.system.entity.ScheduleLog;
import org.liezi.modules.system.service.IScheduleLogService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author: lake.lei
 * @date: 2019/2/23 14:13
 * @description: 定时任务
 */
public class QuartzJob extends QuartzJobBean {
	private Logger logger = LogManager.getLogger(QuartzJob.class);
	private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
        		.get(ScheduleJob.JOB_PARAM_KEY);
        //获取spring bean
		IScheduleLogService scheduleLogService = (IScheduleLogService) SpringContextUtils.getBean("scheduleLogService");
		IGeneratorIDService generatorIDService = (IGeneratorIDService) SpringContextUtils.getBean("generatorIDService");
        ScheduleLog log = new ScheduleLog();
        log.setJobId(scheduleJob.getId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateDt(new Date());
        log.setId(generatorIDService.generatorStringID());
        //任务开始时间
        long startTime = System.currentTimeMillis();
        try {
            //执行任务
        	logger.info("任务准备执行，任务ID：" + scheduleJob.getId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
            		scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);
			future.get();
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(0);
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getId(), e);
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleLogService.save(log);
		}
    }
}
