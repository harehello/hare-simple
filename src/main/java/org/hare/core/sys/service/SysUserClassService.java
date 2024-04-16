package org.hare.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hare.core.sys.entity.SysUserClass;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户班级关系 服务类
 * </p>
 *
 * @author hare
 * @since 2024-03-10
 */
public interface SysUserClassService extends IService<SysUserClass> {


    /**
     * 根据班级id查询用户
     * @param classIds
     * @return
     */
    Set<Long> listUser(Collection<Long> classIds);

    /**
     * 根据用户id查询班级
     * @param userIds
     * @return
     */
    Set<Long> listClass(Collection<Long> userIds);

    /**
     * 保存
     * @param userId
     * @param deptIds
     */
    boolean save(Long userId, List<Long> deptIds);
}
