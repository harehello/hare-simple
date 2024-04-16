package org.hare.core.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.hare.core.sys.entity.SysClass;
import org.hare.core.sys.service.SysClassService;
import org.hare.framework.web.domain.R;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
* <p>
* 班级 前端控制器
* </p>
*
* @author hare
* @since 2024-03-10
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/class")
public class SysClassController {
    private final SysClassService service;

    /**
    * 添加
    *
    * @return R
    */
    @PostMapping
    public R post(@RequestBody SysClass body) {
        service.save(body);
        return R.success();
    }

    /**
    * 修改
    *
    * @return R
    */
    @PutMapping
    public R put(@RequestBody SysClass body) {
        service.updateById(body);
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
    * 分页数据列表
    *
    * @return R
    */
    @GetMapping("/page")
    public R page(Page<SysClass> page, SysClass query) {

        Page<SysClass> data = service.page(page, wrapper(query));

        return R.success(data);
    }

    /**
    * 数据列表
    *
    * @return R
    */
    @GetMapping("/list")
    public R list(SysClass query) {

        List<SysClass> data = service.list(wrapper(query));

        return R.success(data);
    }

    /**
    * 查询条件
    */
    private LambdaQueryWrapper<SysClass> wrapper(SysClass query) {

        return new LambdaQueryWrapper<SysClass>()
                .like(StringUtils.isNotBlank(query.getName()), SysClass::getName, query.getName());
    }

}
