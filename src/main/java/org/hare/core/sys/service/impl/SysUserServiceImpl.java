package org.hare.core.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hare.common.constant.Constants;
import org.hare.core.sys.entity.SysUser;
import org.hare.core.sys.mapper.SysUserMapper;
import org.hare.core.sys.service.SysUserService;
import org.hare.framework.exception.BaseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hare
 * @since 2024-03-09
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(SysUser entity) {
        // 用户名就是手机号
        entity.setUsername(entity.getPhone());
        if ("student".equals(entity.getRole())) {
            // 学生学号是用户账号
            entity.setUsername(entity.getNumber());
        }

        // 用户名是否存在
        SysUser target = findByUsername(entity.getUsername());
        if (Objects.nonNull(target)) {
            throw new BaseException("用户名已存在");
        }

        // 默认班级无
        entity.setClassName(Constants.NONE);
        // 默认密码
        entity.setPassword(getDefaultPassword());

        return super.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(SysUser entity) {

        userCannotUpdate(entity);

        // 用户名就是手机号
        entity.setUsername(entity.getPhone());
        if ("student".equals(entity.getRole())) {
            // 学生学号是用户账号
            entity.setUsername(entity.getNumber());
        }

        // 用户名是否存在 如果用户名已存在切不是当前用户代表重复
        SysUser target = findByUsername(entity.getUsername());
        if (Objects.nonNull(target) && !entity.getId().equals(target.getId())) {
            throw new BaseException("用户名已存在");
        }

        return update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, entity.getId())
                .set(Objects.isNull(target), SysUser::getUsername, entity.getUsername())
                .set(SysUser::getPhone, entity.getPhone())
                .set(SysUser::getNumber, entity.getNumber())
                .set(SysUser::getAge, entity.getAge())
                .set(SysUser::getSex, entity.getSex())
                .set(SysUser::getNickname, entity.getNickname())
                .set(SysUser::getStatus, entity.getStatus())
                .set(SysUser::getSubject, entity.getSubject())
                .set(SysUser::getDescription, entity.getDescription())
                .set(SysUser::getRemark, entity.getRemark())
        );
    }

    @Override
    public boolean updatePassword(String pw, String rawPassword, String username) {

        SysUser user = findByUsername(username);
        boolean matches = passwordEncoder.matches(rawPassword, user.getPassword());
        if (!matches) {
            throw new BaseException("原密码不正确");
        }
        Assert.hasText(pw, "密码格式错误");

        pw = passwordEncoder.encode(pw);

        return update(new UpdateWrapper<SysUser>().lambda()
                .set(SysUser::getPassword, pw)
                .eq(SysUser::getId, user.getId()));
    }

    @Override
    public boolean resetPassword(Long id) {
        SysUser user = getById(id);
        userCannotUpdate(user);

        return update(new LambdaUpdateWrapper<SysUser>()
                .set(SysUser::getPassword, getDefaultPassword())
                .eq(SysUser::getId, user.getId()));
    }

    @Override
    public SysUser findByUsername(String usernmae) {

        return getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, usernmae));
    }

    /**
     * 获取默认密码
     * @return
     */
    private String getDefaultPassword() {
        return passwordEncoder.encode(Constants.DEFAULT_PASSWORD);
    }

    /**
     * 不可被修改的
     * @param user
     */
    private void userCannotUpdate(SysUser user) {
        if (Objects.equals(Constants.USER_SYSTEM_ID, user.getId())) {
            throw new BaseException("用户信息不可被修改");
        }
    }
}
