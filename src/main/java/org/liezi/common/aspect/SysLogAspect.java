//package org.liezi.common.aspect;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.liezi.base.ReturnEntity;
//import org.liezi.common.annotation.LogFiled;
//import org.liezi.common.annotation.SysLog;
//import org.liezi.common.utils.HttpContextUtils;
//import org.liezi.common.utils.IPAddressUtil;
//import org.liezi.common.utils.StringUtils;
//import org.liezi.common.utils.SystemConstants;
//import org.liezi.modules.common.service.IGeneratorIDService;
//import org.liezi.modules.system.entity.Log;
//import org.liezi.modules.system.service.ILogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.Date;
//
///**
// * @author: lake.lei
// * @date: 2019/2/15 15:09
// * @description: 系统日志，切面处理类
// */
//@Aspect
//@Component
//public class SysLogAspect {
//	@Autowired
//	private ILogService logService;
//	@Autowired
//	private IGeneratorIDService generatorIDService;
//
//	private Logger logger = LogManager.getLogger(SysLogAspect.class);
//
//	@Pointcut("@annotation(org.liezi.common.annotation.SysLog)")
//	public void logPointCut() {
//
//	}
//
//	@Around("logPointCut()")
//	public Object around(ProceedingJoinPoint point) throws Throwable {
//		long beginTime = System.currentTimeMillis();
//		//执行方法
//		Object result = point.proceed();
//		//执行时长(毫秒)
//		long time = System.currentTimeMillis() - beginTime;
//		//保存日志
//		saveSysLog(point, time);
//		return result;
//	}
//
//	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//
//		Log sysLog = new Log();
//		SysLog syslog = method.getAnnotation(SysLog.class);
//		if(syslog != null){
//			//注解上的描述
//			sysLog.setOperation(syslog.value());
//		}
//		//请求的方法名
//		String className = joinPoint.getTarget().getClass().getName();
//		String methodName = signature.getName();
//		sysLog.setMethod(className + "." + methodName + "()");
//		//请求的参数
//		Object[] args = joinPoint.getArgs();
//		try{
//			//只取第一个类的入参，第二个类不取,第二个类为校验返回类
//			Object object = args[0];
//			//对对象做克隆，防止对字段对脱敏影响到真实数据
//			Object temp = BeanUtils.cloneBean(object);
//			//如果某个字段被注解，则对此字段做脱敏处理
//			Field[] fields = temp.getClass().getDeclaredFields();
//			for (Field field : fields) {
//				//允许访问私有成员变量
//				field.setAccessible(true);
//				if (hasLogAnnotation(field)) {
//					field.set(temp,"--");
//				}
//			}
//			String params = JSON.toJSONString(temp);
//			int len = params.length();
//			//入参过长截取前半部分
//			if(len>= SystemConstants.MAX_VARCHAR){
//				params = params.substring(0,SystemConstants.MAX_VARCHAR-1);
//			}
//			sysLog.setParams(params);
//		}catch (Exception e){
//			logger.error(e.toString());
//		}
//		//返回值
//		Object returnObject = new Object();
//		try {
//			returnObject = joinPoint.proceed();
//		} catch (Throwable throwable) {
//			throwable.printStackTrace();
//		}
//		if(null != returnObject){
//			ReturnEntity returnEntity = (ReturnEntity) returnObject;
//			//设置请求状态及返回信息
//			returnArgsHandler(returnEntity,sysLog);
//		}else{
//			//默认请求正常
//			sysLog.setRequestStatus(1);
//		}
//		//获取request
//		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//		//设置IP地址
//		sysLog.setIp(IPAddressUtil.getIpAddr(request));
//		//用户名
//		String username = StringUtils.currentAccount();
//		sysLog.setUsername(username);
//		sysLog.setRequestTime(time);
//		sysLog.setCreateDt(new Date());
//		sysLog.setId(generatorIDService.generatorStringID());
//		//保存系统日志
//		logService.save(sysLog);
//	}
//
//	/**
//	 * 对返参进行处理
//	 */
//	private void returnArgsHandler(ReturnEntity returnEntity,Log log){
//		String statusCode = returnEntity.getStatusCode();
//		if(statusCode.equals(ReturnEntity.SUCCESS_CODE)){
//			log.setRequestStatus(1);
//			log.setReturnMessage((String)returnEntity.getMessage());
//		}else if(statusCode.equals(ReturnEntity.WARNING_CODE)){
//			log.setRequestStatus(0);
//			log.setReturnMessage((String)returnEntity.getMessage());
//		}else if(statusCode.equals(ReturnEntity.WARNINGS_CODE)){
//			log.setRequestStatus(0);
//			log.setReturnMessage("invalid arguments");
//		}else if(statusCode.equals(ReturnEntity.ERROR_CODE)){
//			log.setRequestStatus(2);
//			log.setReturnMessage((String)returnEntity.getMessage());
//		}else if(statusCode.equals(ReturnEntity.SYSTEM_ERROR_CODE)) {
//			log.setRequestStatus(2);
//			log.setReturnMessage((String)returnEntity.getMessage());
//		}else if(statusCode.equals(ReturnEntity.LOGIN_SUCCESS_CODE)) {
//			log.setRequestStatus(1);
//			log.setReturnMessage((String)returnEntity.getMessage());
//		}else if(statusCode.equals(ReturnEntity.LOGIN_REDIRECT_CODE)) {
//			log.setRequestStatus(1);
//			log.setReturnMessage((String)returnEntity.getMessage());
//		}else if(statusCode.equals(ReturnEntity.LOGIN_FAIL_CODE)) {
//			log.setRequestStatus(0);
//			log.setReturnMessage("invalid token");
//		}
//	}
//
//	private boolean hasLogAnnotation(Field field) {
//		return (null != field.getAnnotation(LogFiled.class));
//	}
//}
