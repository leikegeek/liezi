package org.liezi.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.liezi.base.SuperEntity;
import java.io.Serializable;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:系统配置信息表实体类
 */
@TableName("system_config")
public class Config extends SuperEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String configId;
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


    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
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
        ", configId=" + configId +
        ", paramKey=" + paramKey +
        ", paramValue=" + paramValue +
        ", status=" + status +
        ", remark=" + remark +
        "}";
    }
}
