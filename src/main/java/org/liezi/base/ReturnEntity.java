package org.liezi.base;

import java.io.Serializable;
/**
 *
 * @Author: Lake
 * @Date: 2018/7/15 20:52
 * @Description: 返参工具类
 */
public class ReturnEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 操作成功
	 */
	public static final String SUCCESS_CODE = "00";
    /**
     *返回提示，单个提示从message取信息展示即可
     **/
	public static final String WARNING_CODE = "01";
    /**
     *返回提示，多个提示从data取信息展示
     **/
	public static final String WARNINGS_CODE = "02";
	/**
	 * 操作失败,非服务器问题
	 */
	public static final String ERROR_CODE = "03";
	/**
	 * 系统异常
	 */
	public static final String SYSTEM_ERROR_CODE = "04";
	/**
	 * 登陆成功
	 */
	public static final String LOGIN_SUCCESS_CODE = "10";
	/**
	 * 登录失败
	 */
	public static final String LOGIN_FAIL_CODE = "12";
	/**
	 * 需要跳转
	 */
	public static final String LOGIN_REDIRECT_CODE = "11";
	/**
	 * 无权限 401状态码
	 */
	public static final String UN_AUTHORIZED = "401";


	public static final String ADD_SUCCESS_MSG = "保存成功";

	public static final String ADD_FAIL_MSG ="保存失败";

	public static final String UPDATE_SUCCESS_MSG = "更新成功";

	public static final String UPDATE_FAIL_MSG ="更新失败";

	public static final String DELETE_SUCCESS_MSG = "删除成功";

	public static final String DELETE_FAIL_MSG ="删除失败";

	public static final String SERVER_EXCEPTION = "服务器异常";
	
	public static final String OPERATION_SUCCESS = "操作成功";
	
	public static final String OPERATION_FAIL = "操作失败";

	String statusCode=SUCCESS_CODE;
	Object message;
	Object datas;
	long count;


	public ReturnEntity(String statusCode, Object message, Object datas, long count) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.datas = datas;
		this.count = count;
	}
	
	public ReturnEntity(String statusCode, Object message, Object datas) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.datas = datas;
	}
	public ReturnEntity(Object message, Object datas) {
		super();
		this.message = message;
		this.datas = datas;
	}
	public ReturnEntity(Object datas) {
		super();
		this.datas = datas;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ReturnEntity{" +
				"statusCode='" + statusCode + '\'' +
				", message=" + message +
				", datas=" + datas +
				", count=" + count +
				'}';
	}
}
