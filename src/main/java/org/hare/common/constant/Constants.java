package org.hare.common.constant;

import org.hare.common.domain.OptionResponse;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 公用常量
 * @author hare
 */
public class Constants {
    /**
     * 用户默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 登录错误消息
     */
    public static final String LOGIN_ERROR_MSG = "用户名或密码错误";


    /**
     * 系统管理员账号ID
     */
    public static final Long USER_SYSTEM_ID = 1L;

    public static final String CSV = ",";

    public static final String YES = "是";
    public static final String NO = "否";

    public static final String PASS = "通过";
    public static final String DISPASS = "驳回";
    public static final String WAIT = "待审核";

    public static final String NORMAL = "正常";
    public static final String ABNORMAL = "异常";
    public static final String NONE = "无";

    /**
     * 用户角色
     */
    public enum Role {

        /**
         * 系统管理员
         */
        admin("管理员"),
        /**
         * 普通用户
         */
        ordinary("普通用户");

        private String name;

        Role(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Map<Role, String> getMap() {
            return Arrays.stream(Constants.Role.values())
                    .collect(Collectors.toMap(v -> v, Constants.Role::getName));
        }

        public static Set<OptionResponse> getList() {
            return Arrays.stream(Constants.Role.values())
                    .map(v -> new OptionResponse(v.name(), v.getName()))
                    .collect(Collectors.toSet());
        }
    }


}
