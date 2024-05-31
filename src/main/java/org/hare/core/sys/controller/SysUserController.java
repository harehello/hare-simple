package org.hare.core.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.hare.common.constant.Constants;
import org.hare.core.sys.dto.LoginUserResponse;
import org.hare.core.sys.dto.PasswordRequest;
import org.hare.core.sys.entity.SysUser;
import org.hare.core.sys.service.SysUserService;
import org.hare.framework.web.domain.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hare
 * @since 2024-03-10
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    private final SysUserService service;

    /**
     * 添加
     *
     * @return R
     */
    @PostMapping
    public R post(@RequestBody SysUser body) {
        service.save(body);
        return R.success();
    }

    /**
     * 修改
     *
     * @return R
     */
    @PutMapping
    public R put(@RequestBody SysUser body) {
        service.updateById(body);
        return R.success();
    }

    /**
     * 修改密码
     *
     * @return R
     */
    @PutMapping("/pw")
    public R putpw(@RequestBody PasswordRequest body, Authentication authentication) {
        // 当前登录用户
        final String username = authentication.getName();
        service.updatePassword(body.getNewPassword(), body.getRawPassword(), username);
        return R.success();
    }

    /**
     * 修改密码
     *
     * @return R
     */
    @PutMapping("/resetpw")
    public R putresetpw(@RequestParam Long id) {
        service.resetPassword(id);
        return R.success();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return R
     */
    @DeleteMapping
    public R delete(@RequestBody Long[] ids) {
        service.removeBatchByIds(Arrays.asList(ids));
        return R.success();
    }

    /**
     * 用户信息
     * @return
     */
    @GetMapping(value = { "/info"})
    public R info(Authentication authentication) {
        String username = authentication.getName();
        SysUser user = service.findByUsername(username);
        return R.success(new LoginUserResponse(user, authentication.getAuthorities()));
    }


    /**
     * 分页数据列表
     *
     * @return R
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/page")
    public R page(Page<SysUser> page, SysUser query) {

        Page<SysUser> data = service.page(page, wrapper(query));

        return R.success(data);
    }

    /**
     * 数据列表
     *
     * @return R
     */
    @GetMapping("/list")
    public R list(SysUser query) {

        List<SysUser> data = service.list(wrapper(query));

        return R.success(data);
    }


    /**
     * 查询条件
     */
    private LambdaQueryWrapper<SysUser> wrapper(SysUser query) {

        return new LambdaQueryWrapper<SysUser>()
                // 不查询管理员
                .ne(SysUser::getId, Constants.USER_SYSTEM_ID)
                .eq(StringUtils.isNotBlank(query.getRole()), SysUser::getRole, query.getRole())
                .like(StringUtils.isNotBlank(query.getNickname()), SysUser::getNickname, query.getNickname())
                .orderByDesc(SysUser::getId);
    }

}
