package org.hare.core.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hare.core.sys.entity.SysUserClass;
import org.hare.core.sys.mapper.SysUserClassMapper;
import org.hare.core.sys.service.SysUserClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户班级关系 服务实现类
 * </p>
 *
 * @author hare
 * @since 2024-03-10
 */
@Service
public class SysUserClassServiceImpl extends ServiceImpl<SysUserClassMapper, SysUserClass> implements SysUserClassService {

    @Override
    public Set<Long> listUser(Collection<Long> classIds) {
        return this.getBaseMapper().selectList(new QueryWrapper<SysUserClass>().lambda()
                        .in(SysUserClass::getClassId, classIds))
                .stream().map(SysUserClass::getUserId).collect(Collectors.toSet());
    }

    @Override
    public Set<Long> listClass(Collection<Long> userIds) {
        return this.getBaseMapper().selectList(new QueryWrapper<SysUserClass>().lambda()
                        .in(SysUserClass::getUserId, userIds))
                .stream().map(SysUserClass::getClassId).collect(Collectors.toSet());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean save(Long userId, List<Long> deptIds) {
        // 先删除之前的
        remove(new LambdaQueryWrapper<SysUserClass>().eq(SysUserClass::getUserId, userId));

        return saveBatch(deptIds.stream().map(t -> {
            SysUserClass userClass = new SysUserClass();
            userClass.setUserId(userId);
            userClass.setClassId(t);
            return userClass;
        }).collect(Collectors.toSet()));
    }
}
