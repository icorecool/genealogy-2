package com.zhang.genealogy.service;

import com.zhang.genealogy.model.PlatformUser;

/**
 * 平台用户登录服务类
 *
 * @Auther: zhangchao
 * @Date: 2019/2/15 13:39
 */
public interface PlatformLoginService {

    /**
     * 平台用户登录
     *
     * @param loginName 登录名
     * @param password  密码
     * @return
     */
    PlatformUser login(String loginName, String password);

    /**
     * 校验验证码
     *
     * @param code           web传输的验证码
     * @param verCode        session中的验证码
     * @param generationTime 验证码生成时间
     */
    void checkVerify(String code, String verCode, long generationTime);
}
