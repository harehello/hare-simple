package org.hare.core.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hare
 * @since 2024-03-10
 */
@Getter
@Setter
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色
     * admin 管理员
     */
    private String role;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 状态：正常、限制
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}
