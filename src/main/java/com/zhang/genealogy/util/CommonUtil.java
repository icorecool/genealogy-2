package com.zhang.genealogy.util;


import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 通用工具类
 *
 * @author wuq
 * @date 2019-01-30
 */
public class CommonUtil {
    private static final String PHONE_NUMBER_REGEX = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    /**
     * 工具类不需要实例化
     */
    private CommonUtil() {
    }

    /**
     * 字符串是否为空校验
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 字符串是否不为空校验
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        return true;
    }

    /**
     * 集合是否为空校验
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        if (null == collection || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 集合是否不为空校验
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection collection) {
        if (null == collection || collection.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Map是否为空校验
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        if (null == map || map.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Map是否不为空校验
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map map) {
        if (null == map || map.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 获取前台显示手机号码，格式：188****2341
     *
     * @param phoneNumber 电话号码
     * @return 显示号码
     */
    public static String getShowNumber(String phoneNumber) {
        if (CommonUtil.isEmpty(phoneNumber)) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY, "phoneNumber");
        }
        if (phoneNumber.length() < 11) {
            throw new CommonException(ErrorCode.PARAM_IS_INVALID, "phoneNumber = " + phoneNumber);
        }

        return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(7);
    }

    /**
     * 电话号码格式校验
     *
     * @param phoneNumber 电话号码
     * @return true-格式正确，false-格式错误
     */
    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher m = p.matcher(phoneNumber);
        return m.find();
    }

    /**
     * 根据UUID生成文件名
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取唯一编号
     *
     * @return
     */
    public static String getUniqueID() {
        return UUID.randomUUID().toString();
    }

}
