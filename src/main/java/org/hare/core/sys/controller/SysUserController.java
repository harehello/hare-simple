package org.hare.core.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.hare.common.constant.Constants;
import org.hare.core.sys.dto.PasswordRequest;
import org.hare.core.sys.entity.SysUser;
import org.hare.core.sys.service.SysUserService;
import org.hare.framework.security.util.SecurityContextUtils;
import org.hare.framework.web.domain.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
     * 修改学生信息
     *
     * @return R
     */
    @PutMapping("/student")
    public R putStudent(@RequestBody SysUser body) {
        SysUser user = service.getById(body.getId());
        user.setNickname(body.getNickname());
        user.setPhone(body.getPhone());
        user.setAge(body.getAge());
        user.setSex(body.getSex());
        service.updateById(user);
        return R.success();
    }

    /**
     * 修改密码
     *
     * @return R
     */
    @PutMapping("/pw")
    public R putpw(@RequestBody PasswordRequest body) {
        Long currUserid = SecurityContextUtils.getUserId();
        service.updatePassword(body.getNewPassword(), body.getRawPassword(), currUserid);
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
     * 调班
     *
     * @return R
     */
    @PutMapping("/class")
    public R putClass(@RequestBody SysUser body) {
        service.updateClass(body);
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
    @GetMapping(value = { "/info", "/info/{userId}" })
    public R info(@PathVariable(value = "userId", required = false) Long userId) {
        if (Objects.isNull(userId)) {
            userId = SecurityContextUtils.getUserId();
        }
        SysUser user = service.getById(userId);
        return R.success(user);
    }


    /**
     * 分页数据列表
     *
     * @return R
     */
    @PreAuthorize("hasAuthority('scope1')")
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
                .ne(SysUser::getId, Constants.USER_SYSTEM_ID)// 不查询管理员
                .eq(StringUtils.isNotBlank(query.getRole()), SysUser::getRole, query.getRole())
                .like(StringUtils.isNotBlank(query.getNickname()), SysUser::getNickname, query.getNickname())
                .likeRight(StringUtils.isNotBlank(query.getNumber()), SysUser::getNumber, query.getNumber())
                .like(StringUtils.isNotBlank(query.getClassName()), SysUser::getClassName, query.getClassName())
                .orderByDesc(SysUser::getId);
    }

}
