package org.hare.core.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.hare.common.constant.Constants;
import org.hare.core.sys.dto.LoginRequest;
import org.hare.core.sys.entity.SysUser;
import org.hare.core.sys.service.SysLoginService;
import org.hare.core.sys.service.SysUserService;
import org.hare.framework.security.JwtBuilder;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *     令牌服务实现
 * </p>
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysLoginServiceImpl implements SysLoginService {

    private final SysUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtBuilder jwtBuilder;

    @Override
    public String token(LoginRequest request) {
        final String username = request.getUsername();
        final String password = request.getPassword();

        // 根据账号获取用户信息
        List<SysUser> users = userService.list(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));

        Assert.notEmpty(users, username + " 用户不存在");

        final SysUser sysUser = users.get(0);

        Assert.isTrue(Constants.NORMAL.equals(sysUser.getStatus()), username + " 用户被限制");
        // 验证密码
        Assert.isTrue(passwordEncoder.matches(password, sysUser.getPassword()), "密码错误");

        return jwtBuilder.build(sysUser.getId(), Collections.singletonList(sysUser.getRole()));
    }

}
