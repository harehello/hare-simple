package org.hare.core.sys.controller;

import lombok.RequiredArgsConstructor;
import org.hare.common.constant.Constants;
import org.hare.framework.web.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author hare
 * @since 2024-07-17
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    /**
     * 数据列表
     *
     * @return R
     */
    @GetMapping("/list")
    public R list() {
        return R.success(Constants.Role.getList());
    }
}
