package org.hare.core.sys.service;

import org.hare.core.sys.dto.LoginRequest;

/**
 * 登录接口
 * @author wang cheng
 */
public interface SysLoginService {

    /**
     * 令牌
     * @param request
     * @return
     */
    String token(LoginRequest request);
}
