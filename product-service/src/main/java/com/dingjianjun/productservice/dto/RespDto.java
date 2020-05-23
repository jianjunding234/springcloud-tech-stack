package com.dingjianjun.productservice.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;

@Data
@SuppressWarnings("unchecked")
public class RespDto<T> implements Serializable {
    private static final long serialVersionUID = -741156059402991812L;
    private int code = 0;
    private String message = "success";
    private T data;

    public static RespDto ok() {
        return new RespDto();
    }

    public static RespDto ok(String msg) {
        RespDto respDto = ok();
        respDto.setMessage(msg);
        return respDto;
    }

    public static <T> RespDto ok(T data) {
        RespDto respDto = ok();
        respDto.setData(data);
        return respDto;
    }

    public static <T> RespDto ok(int code, String message, T data) {
        RespDto r = ok(message);
        r.setCode(code);
        r.setData(data);
        return r;
    }


    public static RespDto error() {
        RespDto respDto = new RespDto();
        respDto.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        respDto.setMessage("未知异常，请联系管理员");
        return respDto;
    }


    public static RespDto error(int code, String msg) {
        RespDto respDto = error();
        respDto.setCode(code);
        respDto.setMessage(msg);
        return respDto;
    }

    public static <T> RespDto error(String msg) {
        RespDto respDto = error();
        respDto.setMessage(msg);
        return respDto;
    }

    public static <T> RespDto error(int code, String msg, T data) {
        RespDto respDto = error(code, msg);
        respDto.setData(data);
        return respDto;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
