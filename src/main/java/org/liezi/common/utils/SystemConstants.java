package org.liezi.common.utils;

/**
 * @author: lake.lei
 * @date: 2019/2/23 14:13
 * @description: 系统常量 与应用无关
 */
public class SystemConstants {
    /**
     * 200状态码
     */
    public static final int STATUS_200 = 200;
    /**
     * 空数组
     */
    public static final String EMPTY_ARRAY_STR = "[]";
    /**
     * JSON格式
     */
    public static final String REQUEST_JSON_TYPE = "json";
    /**
     * XML格式
     */
    public static final String REQUEST_XML_TYPE = "xml";
    /**
     * FORM表单
     */
    public static final String REQUEST_FORM_TYPE = "form";
    /**
     * 更新操作
     */
    public static final String UPDATE_OPERATOR = "update";
    /**
     * 新增操作
     */
    public static final String ADD_OPERATOR = "add";
    /**
     * 日期格式化
     */
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    /**
     * MySQL 字符类型最长[最坏的结果]
     */
    public static final int MAX_VARCHAR = 1000;
    /**
     * 使用参数切换数据源标记
     */
    public static final String DS_PARAMS = "DS_PARAMS_Y";

    /**
     * 定时任务枚举
     * @author: lake.lei
     * @date: 2019/3/1 11:15
     * @return:
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}