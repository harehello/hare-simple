package org.hare.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hare.core.sys.entity.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hare
 * @since 2024-03-09
 */
public interface SysUserService extends IService<SysUser> {

    boolean save(SysUser entity);
    boolean updateById(SysUser entity);

    /**
     * 根据id更新密码
     *
     * @param pw 新密码
     * @param rawPassword 原密码
     * @param username
     * @return
     */
    boolean updatePassword(String pw, String rawPassword, String username);

    /**
     * 根据id重置密码
     *
     * @param id
     * @return
     */
    boolean resetPassword(Long id);

    /**
     * 调班
     * @param entity
     * @return
     */
    boolean updateClass(SysUser entity);

    /**
     * 根据用户名查询
     * @param usernmae
     * @return
     */
    SysUser findByUsername(String usernmae);
}
