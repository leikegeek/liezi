package org.liezi.quartz.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * dataSyncTask 为spring bean名称
 * 方法名需和db里面配置的一样
 * @author: lake.lei
 * @date: 2019/2/23 14:13
 * @description: 定时任务配置
 */
@Component("dataSyncTask")
public class DataSyncTask {
	private Logger logger = LogManager.getLogger(getClass());
	/**
	 * @param params
	 * @author: lake.lei
	 * @date: 2019/3/4 10:57
	 * @return: void
	 */
	public void syncData(String params){
		System.out.print("我是一只定时任务，参数是"+params);
	}

}
