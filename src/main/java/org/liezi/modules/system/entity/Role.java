package org.liezi.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.liezi.base.SuperEntity;

import java.io.Serializable;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-13
 * @description:角色实体类
 */
@TableName("system_role")
public class Role extends SuperEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
        ", roleId=" + roleId +
        ", roleName=" + roleName +
        ", remark=" + remark +
        "}";
    }
}
