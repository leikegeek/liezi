package org.liezi.common.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @Author: Lake
 * @Date: 2018/7/15 21:21
 * @Description:路径处理工具类
 */
public class PathUtils {
	protected static String contextPath = null;
	protected static String basePath = null; 
	protected static String realPath = null;
	
	public static String getBasePath(HttpServletRequest request) {
		contextPath = request.getContextPath();
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
		return basePath;
	}
	
	public static String getRealPath(HttpServletRequest request, String path) {
		ServletContext context = request.getSession().getServletContext();
		realPath = context.getRealPath(path);
		realPath = context.getRealPath(path)+"\\";
		return realPath;
	}
	
	public static String getMyRealPath(HttpServletRequest request, String path) {
		ServletContext context = request.getSession().getServletContext();
		realPath = context.getRealPath(path);
		realPath = context.getRealPath(path);
		return realPath;
	}
}
