package org.liezi.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.liezi.base.SuperEntity;
import top.zhumang.crypto.annotation.Decryption;
import top.zhumang.crypto.annotation.Encryption;

import java.io.Serializable;
/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:系统用户实体类
 */
@TableName("system_user")
public class User extends SuperEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 邮箱
     */
    @Decryption(decryptor="CBCDecryptor")
    @Encryption(encryptor="CBCEncryptor")
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
        ", userId=" + userId +
        ", username=" + username +
        ", password=" + password +
        ", salt=" + salt +
        ", email=" + email +
        ", mobile=" + mobile +
        ", status=" + status +
        "}";
    }
}
