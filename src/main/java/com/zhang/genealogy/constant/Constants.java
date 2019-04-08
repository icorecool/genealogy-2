package com.zhang.genealogy.constant;

/**
 * 通用常量类
 *
 * @author zhangchao
 * @date 2019-01-31
 */
public class Constants {

    // 操作成功
    public final static String RESULT_OK_MESSAGE = "操作成功";

    // 通用连接符
    public final static String COMMON_CONNECTOR = "_";

    // session中 平台用户名称、平台用户id、平台用户类型
    public final static String PLATFORM_USER_LOGIN_NAME = "loginName";
    public final static String PLATFORM_USER_SHOW_NAME = "showName";
    public final static String PLATFORM_USER_ID = "id";
    public final static String PLATFORM_USER_TYPE = "type";


    // 平台账号类型 0-管理员，1-普通运营人员
    public final static int CUST_TYPE_ADMIN = 0;
    public final static int CUST_TYPE_ORDINARY = 1;

    // 通用状态 0-正常/1-禁用
    public final static int STATUS_NORMAL = 0;
    public final static int STATUS_PROHIBIT = 1;

    // 表记录删除状态 0-正常，1-删除
    public final static int DELETE_STATUS_OK = 0;
    public final static int DELETE_STATUS_DELETED = 1;

    // 页面路径
    public final static String PAGE_PATH = "/zhang/";

}
