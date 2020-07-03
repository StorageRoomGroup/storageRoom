package com.chenxin.spring.boot.utils;

import lombok.Data;

@Data
public class BaseResult<T> {

    private T data;

    private Integer code = 200;

    private String message = "请求成功";

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult() {

    }

    public BaseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


}
