package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户/员工表单
 * @author hare
 */
@Getter
@Setter
public class PasswordRequest implements Serializable {
    private static final long serialVersionUID = -2353061138476945767L;

    private String newPassword;
    private String rawPassword;

}
