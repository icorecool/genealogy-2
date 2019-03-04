package com.zhang.genealogy.dao;

import com.zhang.genealogy.model.PlatformUser;

/**
 * 平台人员数据持久化接口
 *
 * @author zhangchao
 * @date 2019-01-29
 */
public interface PlatformUserDAO {

    /**
     * 通过编号查询平台运营人员
     *
     * @param id 运营人员编号
     * @return 运营人员对象
     */
    PlatformUser queryById(Long id);

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
    int updateUser(PlatformUser platformUser);

}
