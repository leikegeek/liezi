package org.liezi.common.utils;
/**
 *
 * @Author: Lake
 * @Date: 2018/8/5 11:32
 * @Description: Rediskey
 * */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

}
