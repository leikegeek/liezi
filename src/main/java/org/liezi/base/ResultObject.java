package org.liezi.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @Author: Lake
 * @Date: 2018/7/15 20:52
 * @Description: 返参对象
 */
public class ResultObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static ReturnEntity systemError(Object errorMessage) {
		List<Object> object=new ArrayList<>();
		return new ReturnEntity(ReturnEntity.SYSTEM_ERROR_CODE,errorMessage,object);
	}
	public static ReturnEntity error(Object errorMessage, Object returnObject) {
		List<Object> object=new ArrayList<>();
		object.add(returnObject);
		return new ReturnEntity(ReturnEntity.ERROR_CODE,errorMessage,object);
	}

	public static ReturnEntity success(Object message, Object returnObject) {
		if(returnObject instanceof List){
			return new ReturnEntity(ReturnEntity.SUCCESS_CODE,message,returnObject);
		}else {
			List<Object> object=new ArrayList<>();
			object.add(returnObject);
			return new ReturnEntity(ReturnEntity.SUCCESS_CODE,message,object);
		}
	}

	/**
	 * 无权限
	 * @param message
	 * @return
	 */
	public static ReturnEntity authorized(Object message){
		return new ReturnEntity(ReturnEntity.UN_AUTHORIZED,message,null);
	}

	/**
	 * 登录跳转
	 * @param statusCode
	 * @param message
	 * @param returnObject
	 * @return
	 */
	public static ReturnEntity login(String statusCode,String message,Object returnObject){
		return new ReturnEntity(statusCode,message,returnObject);
	}

	public static ReturnEntity page(Object message, Object returnObject, long count) {
		if(returnObject instanceof List){
			return new ReturnEntity(ReturnEntity.SUCCESS_CODE,message,returnObject,count);
		}else {
			List<Object> object=new ArrayList<>();
			object.add(returnObject);
			return new ReturnEntity(ReturnEntity.SUCCESS_CODE,message,object,count);
		}
	}

    public static ReturnEntity warning(Object message, Object returnObject) {
	    if(null == returnObject){
            return new ReturnEntity(ReturnEntity.WARNING_CODE, message,null);
        }
        if(returnObject instanceof List){
            return new ReturnEntity(ReturnEntity.WARNING_CODE, message,returnObject);
        }else {
            List<Object> object=new ArrayList<>();
            object.add(returnObject);
            return new ReturnEntity(ReturnEntity.WARNING_CODE, message,returnObject);
        }
    }


}
