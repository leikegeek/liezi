package org.liezi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @Author: Lake
 * @Date: 2018/7/15 21:21
 * @Description:日期处理工具类
 */
public class DateUtils {
	
	/**
	 * 根据时间字符串返回时间
	 * @param dateSource
	 * @param pattern
	 * @return
	 * @date 2018年9月5日
	 */
	public static Date format(String dateSource, String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(dateSource);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 根据时间转化为字符串
	 * @param sourceDate
	 * @param pattern
	 * @return
	 * @date 2018年9月5日
	 */
	public static String parse(Date sourceDate, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.format(sourceDate);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 根据时间往前或者往后移动多少时间
	 * @param sourceDate
	 * @param field -- Calendar field. eg: Calendar.MONTH 则表示往后移动几个月
	 * @param amount 数量，可以是正数也可以是负数， 负数表示往前移动时间
	 * @return
	 * @date 2018年9月5日
	 */
	public static Date move(Date sourceDate, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(field, amount);
		return calendar.getTime();
	}
	
	/**
	 * 获取这一天的终点时间
	 * @param sourceDate
	 * @return
	 * @date 2018年9月5日
	 */
	public static Date getEnd(Date sourceDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	/**
	 * 获取该月的第一天
	 * @param month 格式 2018-08
	 * @return
	 * @date 2018年9月5日
	 */
	public static Date getFirstDayOfMonth(String month){
		Date date = format(month, "yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取该月的最后一天
	 * @param month 格式 2018-08
	 * @return
	 * @date 2018年9月5日
	 */
	public static Date getLastDayOfMonth(String month) {
		Date date = format(month, "yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

}
