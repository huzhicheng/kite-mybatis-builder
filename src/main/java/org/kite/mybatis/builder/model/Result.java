package org.kite.mybatis.builder.model;


/**
 * Result
 *
 * @author fengzheng
 * @date 2018/1/9
 */
public class Result {

    private int code = 0;

    private String message = "";

    private Object data;

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
