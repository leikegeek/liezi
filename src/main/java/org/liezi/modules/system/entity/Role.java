package org.liezi.modules.system.entity;

import org.liezi.base.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-05
 * @description:角色实体类
 */
@TableName("system_role")
public class Role extends SuperEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 角色名称
     */
    private String roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", id=" + id +
        ", roleName=" + roleName +
        ", remark=" + remark +
        "}";
    }
}
