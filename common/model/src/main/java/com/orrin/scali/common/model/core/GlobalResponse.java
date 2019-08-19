package com.orrin.scali.common.model.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author orrin on 2019-08-14
 */
public final class GlobalResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code = 200;
    private String message = "";
    private T data;

    public GlobalResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static GlobalResponse success() {
        return new GlobalResponse(200, "", null);
    }

    public static <T> GlobalResponse success(T data) {
        return new GlobalResponse(200, "", data);
    }

    public static GlobalResponse error(int code, String message) {
        return new GlobalResponse(code, message, null);
    }

    public static GlobalResponse error(String message) {
        return new GlobalResponse(500, message, null);
    }

    public static <T> GlobalResponse error(String message, T data) {
        return new GlobalResponse(500, message, data);
    }

    public static <T> GlobalResponse create(int code , String message, T data) {
        return new GlobalResponse(code, message, data);
    }

    public Map<String, Object> toMap(){
        final Map<String, Object> globalResponse = new HashMap<>();
        globalResponse.put("code", this.code);
        globalResponse.put("message", this.message);
        globalResponse.put("data", this.data);
        return globalResponse;
    }
}
