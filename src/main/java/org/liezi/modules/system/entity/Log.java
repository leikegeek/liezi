package org.liezi.modules.system.entity;

import org.liezi.base.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 *
 * @author: lake.lei
 * @date: 2019-02-27
 * @description:系统日志实体类
 */
@TableName("system_log")
public class Log extends SuperEntity implements Serializable {

    private static final long serialVersionUID = -30318569891334523L;
    private String id;
    /**
     * 用户名
     */
    private String username;
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
    private long requestTime;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 请求状态 0 未完成 1已完成 2失败
     */
    private Integer requestStatus;
    /**
     * 返回提示信息
     */
    private String returnMessage;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", operation='" + operation + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", requestTime=" + requestTime +
                ", ip='" + ip + '\'' +
                ", requestStatus=" + requestStatus +
                ", returnMessage='" + returnMessage + '\'' +
                '}';
    }
}
