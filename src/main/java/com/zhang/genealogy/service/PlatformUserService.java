package com.zhang.genealogy.service;

import com.zhang.genealogy.model.PlatformUser;

/**
 * 平台运营人员服务接口
 *
 * @author wuq
 * @date 2019-01-29
 */
public interface PlatformUserService {

    /**
     * 通过编号查询平台运营人员
     *
     * @param id 运营人员编号
     * @return 运营人员对象
     */
    PlatformUser queryUserById(Long id);

    /**
     * 通过登录用户名查询平台运营人员
     *
     * @param loginName 登录用户名
     * @return 运营人员对象
     */
    PlatformUser queryByLoginName(String loginName);

    /**
     * 更新平台运营人员
     *
     * @param platformUser 平台运营人员
     * @return 操作结果，0-未成功，1-成功
     */
    int modifyUser(PlatformUser platformUser);

    /**
     * 修改平台运营人员密码
     *
     * @param id       平台运营人员主键
     * @param password 新密码
     * @return
     */
    int changeUserPassword(Long id, String password);
}
