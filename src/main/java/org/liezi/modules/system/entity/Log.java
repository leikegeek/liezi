package org.liezi.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.liezi.base.SuperEntity;

import java.io.Serializable;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-13
 * @description:系统日志实体类
 */
@TableName("system_log")
public class Log extends SuperEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long logId;
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
    private Long time;
    /**
     * IP地址
     */
    private String ip;


    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
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
        ", logId=" + logId +
        ", username=" + username +
        ", operation=" + operation +
        ", method=" + method +
        ", params=" + params +
        ", time=" + time +
        ", ip=" + ip +
        "}";
    }
}
