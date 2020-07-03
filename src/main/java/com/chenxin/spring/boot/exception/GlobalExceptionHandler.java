package com.chenxin.spring.boot.exception;

import com.alibaba.fastjson.JSONObject;
import com.chenxin.spring.boot.utils.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object logicExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        BaseResult result = new BaseResult();
        result.setCode(-1);
        result.setMessage("系统异常");
        //如果是业务逻辑异常，返回具体的错误码与提示信息
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            result = businessException.getResult();
        } else {
            //对系统级异常进行日志记录
            logger.error("系统异常:" + e.getMessage(), e);
        }
        return JSONObject.toJSON(result);
    }

}
