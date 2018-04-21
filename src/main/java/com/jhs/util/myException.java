package com.jhs.util;

/**
 * @author: TangNengFa
 * @descption: 自定义异常处理
 * @create: 2018-04-18-17-46
 **/
public class myException extends Exception {
    private int code;
    private String status;
    private String message;

    /**
    * @Description:  构造异常结果
    * @Param: [code, message]
    * @return:
    * @Author: TangNengFa
    * @Date: 2018/04/18
    */
    public myException(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

   /**
    * @Description:
    * @Param: [errorCode]
    * @return:
    * @Author: TangNengFa
    * @Date:  2018/04/18
    */
    public myException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
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

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
