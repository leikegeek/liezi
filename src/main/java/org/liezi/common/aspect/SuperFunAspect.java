package org.liezi.common.aspect;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.liezi.base.ResultObject;
import org.liezi.base.ReturnEntity;
import org.liezi.common.annotation.LogFiled;
import org.liezi.common.annotation.SuperFun;
import org.liezi.common.utils.HttpContextUtils;
import org.liezi.common.utils.IPAddressUtil;
import org.liezi.common.utils.StringUtils;
import org.liezi.common.utils.SystemConstants;
import org.liezi.modules.common.service.IGeneratorIDService;
import org.liezi.modules.system.entity.Log;
import org.liezi.modules.system.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Lake
 * @description: 超级切面
 * @date 2019/3/20
 */
@Aspect
@Component
public class SuperFunAspect {
    @Autowired
    private ILogService logService;
    @Autowired
    private IGeneratorIDService generatorIDService;

    private Logger logger = LogManager.getLogger(SuperFunAspect.class);

    @Pointcut("@annotation(org.liezi.common.annotation.SuperFun)")
    public void superFunPointCut() {

    }

    @Around("superFunPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        /**
         * 获取目标方法基本参数
         */
        //目标方法签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //目标方法类名
        String className = point.getTarget().getClass().getName();
        //目标方法名
        String methodName = signature.getName();
        //目标方法
        SuperFun superFun = method.getAnnotation(SuperFun.class);
        //入参
        Object[] inParams = point.getArgs();
        /**
         * 数据校验
         */
        boolean validationValue = superFun.validationFun();
        if(validationValue){
            BindingResult validationResult = (BindingResult) inParams[1];
            if(validationResult.hasErrors()){
                for (ObjectError error : validationResult.getAllErrors()) {
                    return ResultObject.warning(error.getDefaultMessage(),null);
                }
            }
        }
        //TODO 加密处理
        /**
         * 执行目标方法
         */
        //返参
        Object result = new Object();
        String exceptionMsg = new String();
        try{
            result = point.proceed();
        }catch (Exception e){
            exceptionMsg = e.getMessage();
        }
        /**
         * 保存日志
         */
        String logValue = superFun.logFun();
        if(StringUtils.isNotEmpty(logValue)){
            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveSysLog(logValue,className,methodName,inParams[0],time,result,exceptionMsg);
        }
        // TODO 解密处理
        return result;
    }

    private void saveSysLog(String logDescr,String className,String methodName, Object object,long time,Object returnObject,String exceptionMsg) {
        Log log = new Log();
        log.setOperation(logDescr);
        log.setMethod(className + "." + methodName + "()");
        try{
            //对对象做克隆，防止对字段对脱敏影响到真实数据
            Object temp = BeanUtils.cloneBean(object);
            //如果某个字段被注解，则对此字段做脱敏处理
            Field[] fields = temp.getClass().getDeclaredFields();
            for (Field field : fields) {
                //允许访问私有成员变量
                field.setAccessible(true);
                if (hasLogAnnotation(field)) {
                    field.set(temp,"--");
                }
            }
            String params = JSON.toJSONString(temp);
            int len = params.length();
            //入参过长截取前半部分
            if(len>= SystemConstants.MAX_VARCHAR){
                params = params.substring(0,SystemConstants.MAX_VARCHAR-1);
            }
            log.setParams(params);
        }catch (Exception e){
            logger.error(e.toString());
        }
        //返回值,切记不可多次调用point.proceed()方法获取返回值,多次调用会导致被切方法重复执行
        if(null != returnObject){
            ReturnEntity returnEntity;
            try{
                returnEntity =  (ReturnEntity) returnObject;
            }catch (ClassCastException e){
                returnEntity = ResultObject.systemError(exceptionMsg);
            }
            returnArgsHandler(returnEntity,log);
        }else{
            //默认请求正常
            log.setRequestStatus(1);
        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        log.setIp(IPAddressUtil.getIpAddr(request));
        log.setTime(time);
        log.setCreateDt(new Date());
        log.setLogId(generatorIDService.generatorStringID());
        //保存系统日志
        logService.save(log);
    }

    /**
     * 对返参进行处理
     */
    private void returnArgsHandler(ReturnEntity returnEntity,Log log){
        String statusCode = returnEntity.getStatusCode();
        if(statusCode.equals(ReturnEntity.SUCCESS_CODE)){
            log.setRequestStatus(1);
            log.setReturnMessage((String)returnEntity.getMessage());
        }else if(statusCode.equals(ReturnEntity.WARNING_CODE)){
            log.setRequestStatus(0);
            log.setReturnMessage((String)returnEntity.getMessage());
        }else if(statusCode.equals(ReturnEntity.WARNINGS_CODE)){
            log.setRequestStatus(0);
            log.setReturnMessage("invalid arguments");
        }else if(statusCode.equals(ReturnEntity.ERROR_CODE)){
            log.setRequestStatus(2);
            log.setReturnMessage((String)returnEntity.getMessage());
        }else if(statusCode.equals(ReturnEntity.SYSTEM_ERROR_CODE)) {
            log.setRequestStatus(2);
            log.setReturnMessage((String)returnEntity.getMessage());
        }else if(statusCode.equals(ReturnEntity.LOGIN_SUCCESS_CODE)) {
            log.setRequestStatus(1);
            log.setReturnMessage((String)returnEntity.getMessage());
        }else if(statusCode.equals(ReturnEntity.LOGIN_REDIRECT_CODE)) {
            log.setRequestStatus(1);
            log.setReturnMessage((String)returnEntity.getMessage());
        }else if(statusCode.equals(ReturnEntity.LOGIN_FAIL_CODE)) {
            log.setRequestStatus(0);
            log.setReturnMessage("invalid token");
        }
    }

    private boolean hasLogAnnotation(Field field) {
        return (null != field.getAnnotation(LogFiled.class));
    }
}
