package org.hare.framework.web.domain;

import org.springframework.http.HttpStatus;

/**
 * 返回结果状态
 * @author hare
 */
public enum ResultStatus {

    FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "内部服务器错误"),
    SUCCESS(HttpStatus.OK.value(), "操作成功"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "授权无效"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "没有权限");


    private final int code;
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
