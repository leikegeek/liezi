package org.liezi.modules.system.entity;

import org.liezi.base.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-05
 * @description:系统配置信息表实体类
 */
@TableName("system_config")
public class Config extends SuperEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * key
     */
    private String paramKey;
    /**
     * value
     */
    private String paramValue;
    /**
     * 状态   0：隐藏   1：显示
     */
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

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
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
        return "Config{" +
        ", id=" + id +
        ", paramKey=" + paramKey +
        ", paramValue=" + paramValue +
        ", status=" + status +
        ", remark=" + remark +
        "}";
    }
}
