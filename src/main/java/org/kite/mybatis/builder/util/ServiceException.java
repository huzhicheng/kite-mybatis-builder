package org.kite.mybatis.builder.util;

/**
 * ServiceException
 *
 * @author fengzheng
 * @date 2018/1/8
 */
public class ServiceException extends RuntimeException{

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message,Throwable cause){
        super(message,cause);
    }

    public ServiceException(String message, int errorCode){
        this(message);
        this.errorCode = errorCode;
    }

    public ServiceException(String message, int errorCode, Throwable cause){
        this(message,cause);
        this.errorCode = errorCode;
    }

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
