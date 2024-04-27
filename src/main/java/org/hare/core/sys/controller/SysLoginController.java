package org.hare.core.sys.controller;

import lombok.RequiredArgsConstructor;
import org.hare.core.sys.dto.LoginRequest;
import org.hare.core.sys.service.SysLoginService;
import org.hare.framework.web.domain.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录/登出
 * @author hare
 */
@RequiredArgsConstructor
@RestController
public class SysLoginController {
    private final SysLoginService loginService;


    /**
     * 获取token
     * @param loginRequest
     * @return
     */
    @PostMapping("/token")
    public R token(@RequestBody LoginRequest loginRequest) {
        return R.success(loginService.token(loginRequest));
    }

}