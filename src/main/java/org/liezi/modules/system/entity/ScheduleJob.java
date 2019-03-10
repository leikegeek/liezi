package org.liezi.modules.system.entity;

import org.liezi.base.SuperEntity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-01
 * @description:定时任务实体类
 */
public class ScheduleJob extends SuperEntity implements Serializable {
    private static final long serialVersionUID = -5147328648020789833L;
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    /**
     * 任务id
     */
    private String id;
    /**
     * spring bean名称
     */
    @NotBlank(message="bean名称不能为空")
    private String beanName;
    /**
     * 方法名
     */
    @NotBlank(message="方法名称不能为空")
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * cron表达式
     */
    @NotBlank(message="cron表达式不能为空")
    private String cronExpression;
    /**
     * 任务状态  0：正常  1：暂停
     */
    @NotBlank(message="状态不能为空")
    private Integer status;
    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "QuartzJob{" +
        ", id=" + id +
        ", beanName=" + beanName +
        ", methodName=" + methodName +
        ", params=" + params +
        ", cronExpression=" + cronExpression +
        ", status=" + status +
        ", remark=" + remark +
        "}";
    }
}
