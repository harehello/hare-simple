package org.hare.framework.security.util;

import org.hare.framework.exception.BaseException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security上下文工具类
 * @author hare
 */
public class SecurityContextUtils {


    public static Authentication getAuthentication() {
        try {
            return SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
            throw new BaseException("获取用户信息异常");
        }
    }

    public static Long getUserId() {
        try {
            return Long.parseLong(getAuthentication().getName());
        } catch (Exception e) {
            throw new BaseException("获取用户信息异常");
        }
    }

}
