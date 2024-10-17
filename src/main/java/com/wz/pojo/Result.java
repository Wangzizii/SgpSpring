package com.wz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//统一响应结果
public class Result<T>  {
    private int code;
    private String msg;
    private T data;
    public static <E> Result<E> success(String  msg,E data) {
        return new Result<E>(200, msg, data);
    }
    public static <E>Result<E> success(String  msg) {
        return new Result<E>(200, msg, null);
    }
    public static  <E>Result<E> error(int code,String msg,E data) {
        return new Result<E>(code, msg, data);
    }
    public static  Result<String> error(String msg) {
        return new Result<String>(500, msg, null);
    }
}
