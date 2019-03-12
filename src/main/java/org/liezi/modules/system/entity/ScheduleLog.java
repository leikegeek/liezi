package org.liezi.modules.system.entity;

import org.liezi.base.SuperEntity;

import java.io.Serializable;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-01
 * @description:定时任务日志实体类
 */
public class ScheduleLog extends SuperEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 任务日志id
     */
    private Long logId;
    /**
     * 任务id
     */
    private Long jobId;
    /**
     * spring bean名称
     */
    private String beanName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * 任务状态    0：成功    1：失败
     */
    private Integer status;
    /**
     * 失败信息
     */
    private String error;
    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "ScheduleLog{" +
                "logId='" + logId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", times=" + times +
                '}';
    }
}
