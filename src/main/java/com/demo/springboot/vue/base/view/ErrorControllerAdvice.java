package com.demo.springboot.vue.base.view;

import com.demo.springboot.vue.base.servcie.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1：此类由组件扫描注册后处理Service层抛出的ServiceException、DataAccessException，通过视图跳转到BaseErrorController同一处理<br/>
 * 2：MVC的代码中抛出的异常由DefaultHandlerExceptionResolver转化后经由web.xml中指定的error_page跳转到BaseErrorController处理<br/>
 */
@RestControllerAdvice(annotations = RestController.class)
public class ErrorControllerAdvice {
    private static Log logger = LogFactory.getLog(ExceptionHandler.class);

    /**
     * 处理ServiceException，跳转到BaseErrorController.java,也可以对ServiceException的子类做出处理
     *
     * @param request
     * @param response
     * @param object
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    public ModelAndView errorHandler(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
        // 设置响应状态码为500，也可以去设置响应的内容类型，然后使用BaseErrorController生成对应格式的错误信息
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        ModelAndView modelAndView = new ModelAndView("forward:/error");

        // 传递异常的相关信息
        request.setAttribute("javax.servlet.error.status_code",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        request.setAttribute("javax.servlet.error.message",
                exception.getMessage());

        return modelAndView;
    }


    /**
     * 处理DataAccessException，跳转到BaseErrorController.java,也可以对DataAccessException的子类做出处理
     *
     * @param request
     * @param response
     * @param object
     * @param exception
     * @return
     */
    @ExceptionHandler(value = DataAccessException.class)
    public ModelAndView handlerDataAccessException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
        // 设置响应状态码为500，也可以去设置响应的内容类型，然后使用BaseErrorController生成对应格式的错误信息
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        ModelAndView modelAndView = new ModelAndView("forward:/error");

        // 传递异常的相关信息
        request.setAttribute("javax.servlet.error.status_code",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        request.setAttribute("javax.servlet.error.message",
                exception.getMessage());

        return modelAndView;
    }
}
