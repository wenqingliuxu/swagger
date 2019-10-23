package com.demo.springboot.vue.base.view;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 封装返回给restful客户端的消息：
 *
 * {success:true|false, data:[]|{} [, error_code:, error_message:] }
 * * @author 王庆丰
 */
public class PackResponse {

    // 通过success字段来表明这个请求的成功或失败
    private boolean success = true;

    //data数据
    private Object data;

    //自定义的错误码,不为空时序列化
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error_code;

    //自定义的错误消息,不为空时序列化
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error_message;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
