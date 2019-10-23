package com.demo.springboot.vue.base.view;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 取代有Spring Boot自动注册的异常处理器
 */
@RestController
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    public final Log logger = LogFactory.getLog(this.getClass());


    @Override
    public String getErrorPath() {
        logger.info("这是错误页面");
        return "error";
    }

    /**
     * 处理json请求的异常
     *
     * @param request
     * @return
     */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     public PackResponse handleJSON(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.info("handleJSON():....................");

        logger.warn(request.getAttribute("javax.servlet.error.status_code"));
        logger.warn(request.getAttribute("javax.servlet.error.message"));

        //避免被负载均衡、代理等强制处理4、5开头的响应
        //response.reset();//先重置，如果已经有请求内容生成，则直接重写Status无效,需要先reset
        // response.reset()会导致DefaultCorsProcessor设置的cors响应头也重置，导致出错时客户端收到的响应没有CORS响应头
       response.setStatus(HttpStatus.OK.value());


        PackResponse packResponse = new PackResponse();
        packResponse.setSuccess(false);

        //实际应用的话，此处的状态码应该自定义
        packResponse.setError_code(request.getAttribute("javax.servlet.error.status_code").toString());
        packResponse.setError_message(request.getAttribute("javax.servlet.error.message").toString());

        return packResponse;
    }
}