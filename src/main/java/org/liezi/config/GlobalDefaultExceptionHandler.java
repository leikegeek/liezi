package org.liezi.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.liezi.base.ResultObject;
import org.liezi.base.ReturnEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author Lake
 * @Description //TODO
 * @Date 2018/7/15 21:07
 * @Param  全局配置
 **/
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	private static final Logger logger  =  LogManager.getLogger(GlobalDefaultExceptionHandler.class );
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ReturnEntity defaultExceptionHandler(HttpServletRequest req, Exception e) {
		e.printStackTrace();
        logger.error("error==>"+e.getMessage());
		return ResultObject.systemError(ReturnEntity.SERVER_EXCEPTION);
	}
	
}
