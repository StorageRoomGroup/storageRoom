package com.chenxin.spring.boot.exception;

import com.chenxin.spring.boot.utils.BaseResult;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private BaseResult result = new BaseResult();

    private BusinessException(Integer code, String message) {
        super(message);
        result.setCode(code);
        result.setMessage(message);
    }

    /**
     * 抛出逻辑异常
     *
     * @param
     * @return
     */
    public static BusinessException error(Integer code, String message) {
        return new BusinessException(code, message);
    }


}
