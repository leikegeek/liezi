package org.liezi.common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.liezi.base.ResultObject;
import org.liezi.base.ReturnEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author: lake.lei
 * @date: 2019/2/15 16:04
 * @description: 异常处理类
 */
public class LieziExceptionHandler {

    private Logger logger = LogManager.getLogger(LieziExceptionHandler.class);

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(LieziException.class)
    public ReturnEntity handleDjiException(LieziException e){
        logger.error(e.getMessage(), e);
        return ResultObject.systemError("system exception");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ReturnEntity handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultObject.systemError("not found path");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ReturnEntity handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return ResultObject.systemError("duplicate data in databases");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ReturnEntity handleAuthorizationException(AuthorizationException e){
        logger.error(e.getMessage(), e);
        return ResultObject.systemError("no authorization");
    }

    @ExceptionHandler(Exception.class)
    public ReturnEntity handleException(Exception e){
        logger.error(e.getMessage(), e);
        return ResultObject.systemError("system exception");
    }

}