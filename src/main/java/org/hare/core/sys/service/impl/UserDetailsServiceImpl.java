package org.hare.core.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.hare.common.constant.Constants;
import org.hare.core.sys.dto.LoginUserDTO;
import org.hare.core.sys.entity.SysUser;
import org.hare.core.sys.service.SysUserService;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;

/**
 * Security 的 UserDetailsService实现
 * @author hare
 */
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        SysUser sysUser = userService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));

        if (Objects.isNull(sysUser)) {
            throw new RuntimeException(username + "用户不存在");
        }

        if (!Constants.NORMAL.equals(sysUser.getStatus())) {
            throw new DisabledException(username + "登录被限制");
        }

        SimpleGrantedAuthority app = new SimpleGrantedAuthority("app");
        final HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(app);
        return new LoginUserDTO(sysUser, authorities);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
