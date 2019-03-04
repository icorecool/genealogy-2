package com.zhang.genealogy.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 平台人员实体类
 *
 * @author zhangchao
 * @date 2019-01-29
 */
@Getter
@Setter
public class PlatformUser {

    /**
     * 用户编号，主键
     */
    private Long id;

    /**
     * 用户显示名称
     */
    private String userShowName;

    /**
     * 登录用户名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户状态，0-正常，1-禁用
     */
    private Integer userStatus;

    /**
     * 用户类型，0-管理员，1-普通运营人员
     */
    private Integer userType;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
}
