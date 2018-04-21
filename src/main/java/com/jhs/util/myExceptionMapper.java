package com.jhs.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author: TangNengFa
 * @descption: 异常处理器
 * @create: 2018-04-18-18-10
 **/
@Provider
public class myExceptionMapper implements ExceptionMapper<Exception> {
    public Response toResponse(Exception e) {
        Response.ResponseBuilder responseBuilder = null;
        if (e instanceof myException) {
            //截取自定义类型
            myException exp = (myException) e;
            ErrorEntity errorEntity = new ErrorEntity(exp.getCode(), exp.getStatus(), exp.getMessage());
            responseBuilder = Response.ok(errorEntity,MediaType.APPLICATION_JSON);
        } else {
            ErrorEntity errorEntity = new ErrorEntity(
                    ErrorCode.ERROR_CODE.getCode(),
                    ErrorCode.ERROR_CODE.getStatus(),
                    ErrorCode.ERROR_CODE.getMessage());
            responseBuilder = Response.ok(errorEntity,MediaType.APPLICATION_JSON);
        }
        return responseBuilder.build();
    }
}
