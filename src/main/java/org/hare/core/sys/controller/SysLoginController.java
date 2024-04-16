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

//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
    private final SysLoginService loginService;

//    /**
//     * 登录
//     * @param loginBodyDTO
//     * @return
//     */
//    @PostMapping("/login")
//    public R login(@RequestBody LoginRequest loginBodyDTO, HttpServletRequest request) {
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginBodyDTO.getUsername(), loginBodyDTO.getPassword());
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        LoginUserDTO loginUserDTO = (LoginUserDTO) authentication.getPrincipal();
//
//        Long userId = loginUserDTO.getUser().getId();
//        String role = loginUserDTO.getUser().getRole();
//        String token = jwtService.createToken(userId, loginUserDTO.getUsername(), role);
//
//        Map<String, Object> response = new HashMap<String, Object>(3);
//        response.put(SecurityUtils.TOKEN, token);
//        response.put("user", loginUserDTO.getUser());
//        response.put("authorities", loginUserDTO.getAuthorities());
//        return R.success(response);
//    }


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