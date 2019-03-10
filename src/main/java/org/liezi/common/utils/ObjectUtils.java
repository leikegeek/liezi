package org.liezi.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 *
 * @Author: Lake
 * @Date: 2018/7/15 21:21
 * @Description: 对象转化工具类
 */
public class ObjectUtils {

	/**
	 * 对象转Map, 会过滤掉static、 final修饰的字段和值为null的属性 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> object2Map(Object obj){
		try {
			Class<? extends Object> clazz = obj.getClass();
			Field[] declaredFields = clazz.getDeclaredFields();
			Map<String, Object> map = new HashMap<>(16);
			doField(declaredFields,map,obj);
			Class<?> superclass = clazz.getSuperclass();
			while (superclass != null) {
				Field[] fields = superclass.getDeclaredFields();
				doField(fields,map,obj);
				superclass = superclass.getSuperclass();
			}
			return map;
		} catch (Exception e) {
			return null;
		}
	}

	
	public static <T> T map2Object(Map<String, Object> map, Class<T> clazz){
		try {
			T t =  clazz.newInstance();
			for ( Entry<String, Object> entry: map.entrySet()) {
				String key = entry.getKey();
				try {
					Field field = clazz.getDeclaredField(key);
					field.setAccessible(true);
					field.set(t, entry.getValue());
				} catch (Exception e){
					set(t, entry);
				}
			}
			return t;
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	static void set(Object obj, Entry<String, Object> entry) {
		Class<?> superclass = obj.getClass().getSuperclass();
		while (superclass != null) {
			try {
				Field declaredField = superclass.getDeclaredField(entry.getKey());
				declaredField.setAccessible(true);
				declaredField.set(obj, entry.getValue());
			} catch (Exception e) {
				
			}
			superclass = superclass.getSuperclass();
		}
	}
	
	/**
	 * 对象转对象，字段相同可能转成功
	 * @param from
	 * @param clazz
	 * @return
	 */
	public static <T> T obj2obj(Object from, Class<T> clazz){
		try {
			Map<String, Object> map = object2Map(from);
			return map2Object(map, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 批量对象转换
	 * @param objects
	 * @param clazz
	 * @return
	 * @author o-charlie.guo
	 * @date 2018年8月8日
	 */
	public static <T> Collection<T> objs2objs(Collection<?> objects, Class<T> clazz){
		List<T> results = new ArrayList<T>();
		for (Object obj : objects) {
			T t = obj2obj(obj, clazz);
			if (t != null) {
				results.add(t);
			}
		}
		return results;
	}
	
	/**
	 * 获取字段名
	 * @param object
	 * @return
	 */
	public static List<String> getFieldName(Object object) {
		List<String> list = new ArrayList<>();
		Class<?> clazz;
		try {
			// 得到catClass类所有的属性（包括私有属性）
			clazz = Class.forName(object.getClass().getName());
			Field[] fileds = clazz.getDeclaredFields(); 
            for (Field field : fileds) {
            	field.setAccessible(true);
            	String fieldName = field.getName();
            	list.add(fieldName);
            }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	} 
	
	/**
	 * 联合字符串集合
	 * @return
	 * @date 2018年8月28日
	 */
	public static String join(Collection<String> array, String join) {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<String> iterator = array.iterator();
		while(iterator.hasNext()){
			String one = iterator.next();
			if (one == null || one.length() <= 0){
				continue;
			}
			stringBuilder.append(one);
			if (iterator.hasNext()) {
				stringBuilder.append(join);
			}
		}
		String result = stringBuilder.toString();
		if (result.endsWith(join)){
			result = result.substring(0, result.lastIndexOf(join));
		}
		return result;
	}
	
	/**
	 * 分割字符串
	 * @param value
	 * @param regex
	 * @return
	 * @date 2018年8月29日
	 */
	public static List<String> split(String value, String regex){
		String[] values = value.split(regex);
		List<String> result = new ArrayList<String>();
		for(String val : values){
			if( val==null || val.isEmpty()){
				continue;
			}
			result.add(val);
		}
		return result;
	}
	/**
	 *
	 * @author: lake.lei
	 * @date: 2019/2/22 11:31
	 * @param: [fields, map, obj]
	 * @return: void
	 * @description: 处理实体字段
	 */
	static void doField(Field[] fields,Map<String, Object> map,Object obj){
		for (Field field : fields) {
			field.setAccessible(true);
			if (Modifier.isStatic(field.getModifiers())
					|| Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			Object value = null;
			try {
				value = field.get(obj);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value == null) {
				continue;
			}
			if (value instanceof String
					&& ((String) value).length() == 0) {
				continue;
			}
			map.put(field.getName(), value);
		}
	}
}
