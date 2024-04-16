package org.hare.framework.web.domain;

import java.util.HashMap;

/**
 * 返回结果
 * @author hare
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = -1607247636529276173L;

    public static final String CODE= "code";
    public static final String MSG = "msg";
    public static final String DATA = "data";

    public R(int code, String msg, Object data) {
        put(CODE, code);
        put(MSG, msg);
        put(DATA, data);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    public R msg(String msg) {
        put(MSG, msg);
        return this;
    }
    public R code(int code) {
        put(CODE, code);
        return this;
    }

    public static R of(ResultStatus status) {
        return new R(status.code(), status.msg(), null);
    }
    public static R of(ResultStatus status, Object object) {
        return new R(status.code(), status.msg(), object);
    }

    public static R success() {
        return of(ResultStatus.SUCCESS, null);
    }
    public static R success(Object object) {
        return of(ResultStatus.SUCCESS, object);
    }

    public static R failed() {
        return of(ResultStatus.FAILED, null);
    }
    public static R failed(Object object) {
        return of(ResultStatus.FAILED, object);
    }

    @Override
    public String toString() {
        return String.format("R{ %s: %s; %s: %s; %s: %s }", CODE, this.get(CODE), MSG, this.get(MSG),
                DATA, this.get(DATA));
    }
}

