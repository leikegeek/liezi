package org.liezi.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.liezi.common.annotation.CheckStringArray;
import org.liezi.common.validator.ValidatorPageGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
/**
 *
 * @Author: Lake
 * @Date: 2018/7/15 20:52
 * @Description: 超类
 */
public class SuperEntity {
    /**
     * 创建时间
     */
    @TableField(value = "create_dt",fill = FieldFill.INSERT)
    private Date createDt;
    /**
     * 更新时间
     */
    @TableField(value = "update_dt",fill = FieldFill.INSERT_UPDATE)
    private Date updateDt;
    /**
     * 创建人
     */
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 最后更新人
     */
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 逻辑删除标记位
     */
    @TableField(value = "delete_flag")
    @TableLogic
    private Integer deleteFlag;
    /**
     * 每页size条数据
     * 不映射至DB
     */
    @NotNull(groups ={ValidatorPageGroup.class},message = "每页条数不能为空")
    @Min(value = 1,groups ={ValidatorPageGroup.class},message = "每页条数应大于0")
    @TableField(exist=false)
    private Integer size;
    /**
     * 当前为第几页
     * 不映射至DB
     */
    @NotNull(groups ={ValidatorPageGroup.class},message = "当前页码不能为空")
    @Min(value = 1,groups ={ValidatorPageGroup.class},message = "当前页码应大于0")
    @TableField(exist=false)
    private Integer current;
    /**
     * 正序排序字段
     * 不映射至DB
     */
    @TableField(exist=false)
    @CheckStringArray(value="[a-zA-Z0-9]{1,15}",message="正序排序字段不合法",groups = {ValidatorPageGroup.class})
    private String[] ascs;
    /**
     * 倒序排序字段
     * 不映射至DB
     */
    @TableField(exist=false)
    @CheckStringArray(value="[a-zA-Z0-9]{1,15}",message="倒序排序字段不合法",groups = {ValidatorPageGroup.class})
    private String[] descs;
    /**
     * 搜索关键字
     */
    @TableField(exist=false)
    private String searchValues;

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String[] getAscs() {
        return ascs;
    }

    public void setAscs(String[] ascs) {
        this.ascs = ascs;
    }

    public String[] getDescs() {
        return descs;
    }

    public void setDescs(String[] descs) {
        this.descs = descs;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getSearchValues() {
        return searchValues;
    }

    public void setSearchValues(String searchValues) {
        this.searchValues = searchValues;
    }

    @Override
    public String toString() {
        return "SuperEntity{" +
                "createDt=" + createDt +
                ", updateDt=" + updateDt +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", size=" + size +
                ", current=" + current +
                ", ascs=" + Arrays.toString(ascs) +
                ", descs=" + Arrays.toString(descs) +
                ", searchValues='" + searchValues + '\'' +
                '}';
    }
}
