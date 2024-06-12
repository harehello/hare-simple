package org.hare.framework.web.domain;

import org.springframework.http.HttpStatus;

/**
 * 返回结果状态
 * @author hare
 */
public enum ResultStatus {

    /**
     * 内部服务器错误
     */
    FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "内部服务器错误"),
    /**
     * 操作成功
     */
    SUCCESS(HttpStatus.OK.value(), "操作成功"),
    /**
     * 授权无效
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "授权无效"),

    /**
     * 没有权限
     */
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "没有权限");


    /**
     * 响应码
     */
    private final int code;
    /**
     * 响应消息
     */
    private final String msg;

    ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}
