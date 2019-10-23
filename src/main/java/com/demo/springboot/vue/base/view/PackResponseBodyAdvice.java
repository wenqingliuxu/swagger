package com.demo.springboot.vue.base.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 拦截Restfull接口的返回数据，重新包装
 * 通过拦截器拦截的话是无法实现的，因为@ResponseBody注解的方法在postHandle前已经有响应生成
 * <p>
 * //加了RestController注解的Controller
 *
 * @author 王庆丰
 * @ControllerAdvice(annotations = RestController.class)
 * public class AnnotationAdvice {}
 * <p>
 * // 指定包的所有Controller
 * @ControllerAdvice("org.example.controllers") public class BasePackageAdvice {}
 * <p>
 * // 指定class所有Controller
 * @ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
 * public class AssignableTypesAdvice {}
 */
@RestControllerAdvice(annotations = RestController.class)
public class PackResponseBodyAdvice implements ResponseBodyAdvice {
    private static final Log log = LogFactory.getLog(PackResponseBodyAdvice.class);

    /**
     *
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        log.info(methodParameter);
        log.info(aClass);
        return true;
    }

    /**
     * @param body               Controller返回的数据
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        log.info("beforeBodyWrite.............");

        if (!(body instanceof PackResponse)) {
            log.info("重新打包JSON.............");

            PackResponse packResponse = new PackResponse();
            packResponse.setSuccess(true);
            packResponse.setData(body);
            return packResponse;
        }
        return body;
    }
}
