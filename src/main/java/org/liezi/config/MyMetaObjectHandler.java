package org.liezi.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.liezi.common.utils.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: Lake
 * @date: 2018/7/15 21:09
 * @description: 全局自动填充配置
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler{
    /**
     * 公共字段新增自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createBy = metaObject.getValue("createBy");
        Object updateBy = metaObject.getValue("updateBy");
        String operator = StringUtils.currentAccount();
        if (null == createBy) {
            if(StringUtils.isBlank(operator)){
                metaObject.setValue("createBy","system");
            }else{
                metaObject.setValue("createBy",operator);
            }
        }
        if (null == updateBy) {
            if(StringUtils.isBlank(operator)){
                metaObject.setValue("updateBy","system");
            }else{
                metaObject.setValue("updateBy",operator);
            }
        }
        setFieldValByName("updateDt", new Date(), metaObject);
        setFieldValByName("createDt", new Date(), metaObject);
    }

    /**
     * 公共字段新增填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        String operator = StringUtils.currentAccount();
        if(StringUtils.isBlank(operator)){
            setFieldValByName("updateBy", "system", metaObject);
        }else{
            setFieldValByName("updateBy", operator, metaObject);
        }
        setFieldValByName("updateDt", new Date(), metaObject);
    }

}
