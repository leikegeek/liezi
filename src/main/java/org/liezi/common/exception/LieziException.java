package org.liezi.common.exception;

/**
 * @author: lake.lei
 * @date: 2019/2/15 15:52
 * @description: 自定义异常类
 */
public class LieziException extends RuntimeException{

    private String msg;
    private int code = 500;

    public LieziException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public LieziException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public LieziException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public LieziException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}