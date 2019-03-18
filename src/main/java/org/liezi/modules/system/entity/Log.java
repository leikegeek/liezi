package org.liezi.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.liezi.base.SuperEntity;
import java.io.Serializable;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-18
 * @description:系统日志实体类
 */
@TableName("system_log")
public class Log extends SuperEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String logId;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 执行时长(毫秒)
     */
    private Long time;
    /**
     * 返回信息
     */
    private String returnMessage;
    /**
     * 请求状态 1 成功 0 失败
     */
    private Integer requestStatus;
    /**
     * IP地址
     */
    private String ip;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId='" + logId + '\'' +
                ", operation='" + operation + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", time=" + time +
                ", returnMessage='" + returnMessage + '\'' +
                ", requestStatus=" + requestStatus +
                ", ip='" + ip + '\'' +
                '}';
    }
}
