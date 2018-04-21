package com.jhs.util;

/**
 * @author: TangNengFa
 * @descption: 枚举 异常代码信息
 * @create: 2018-04-18-17-39
 **/
public enum ErrorCode {
    OK_CODE(200, "success", "ok"),ERROR_CODE(500, "error", "内部错误");
    private int code;
    private String status;
    private String message;

    ErrorCode (int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
