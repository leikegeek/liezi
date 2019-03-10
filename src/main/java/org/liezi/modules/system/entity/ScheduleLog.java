package org.liezi.modules.system.entity;

import org.liezi.base.SuperEntity;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-01
 * @description:定时任务日志实体类
 */
public class ScheduleLog extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务日志id
     */
    private String id;
    /**
     * 任务id
     */
    private String jobId;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
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
        ", id=" + id +
        ", jobId=" + jobId +
        ", beanName=" + beanName +
        ", methodName=" + methodName +
        ", params=" + params +
        ", status=" + status +
        ", error=" + error +
        ", times=" + times +
        "}";
    }
}
