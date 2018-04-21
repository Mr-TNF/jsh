package com.jhs.util;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author: TangNengFa
 * @descption: 异常信息实体
 * @create: 2018-04-18-17-44
 **/
@XmlRootElement
public class ErrorEntity {
    private int resp_code;
    private String resp_status;
    private String resp_msg;

    public ErrorEntity(int resp_code, String resp_status, String resp_msg) {
        this.resp_code = resp_code;
        this.resp_status = resp_status;
        this.resp_msg = resp_msg;
    }

    public int getResp_code() {
        return resp_code;
    }

    public void setResp_code(int resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_status() {
        return resp_status;
    }

    public void setResp_status(String resp_status) {
        this.resp_status = resp_status;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }
}
