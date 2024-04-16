package org.hare.core.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.hare.core.sys.entity.SysSemester;
import org.hare.core.sys.service.SysSemesterService;
import org.hare.framework.web.domain.R;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
* <p>
* 学期管理 前端控制器
* </p>
*
* @author hare
* @since 2024-03-10
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/semester")
public class SysSemesterController {
    private final SysSemesterService service;

    /**
    * 添加
    *
    * @return R
    */
    @PostMapping
    public R post(@RequestBody SysSemester body) {
        service.save(body);
        return R.success();
    }

    /**
    * 修改
    *
    * @return R
    */
    @PutMapping
    public R put(@RequestBody SysSemester body) {
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
    public R page(Page<SysSemester> page) {

        Page<SysSemester> data = service.page(page, wrapper());

        return R.success(data);
    }

    /**
    * 数据列表
    *
    * @return R
    */
    @GetMapping("/list")
    public R list() {

        List<SysSemester> data = service.list(wrapper());

        return R.success(data);
    }

    /**
    * 查询条件
    */
    private LambdaQueryWrapper<SysSemester> wrapper() {

        return new LambdaQueryWrapper<SysSemester>();
    }

}
