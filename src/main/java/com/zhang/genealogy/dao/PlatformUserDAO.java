package com.zhang.genealogy.dao;

import com.zhang.genealogy.model.PlatformUser;
import com.zhang.genealogy.qb.PlatformUserQB;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平台运营人员数据持久化接口
 *
 * @author wuq
 * @date 2019-01-29
 */
public interface PlatformUserDAO {

    /**
     * 通过编号查询平台运营人员
     *
     * @param id 运营人员编号
     * @return 运营人员对象
     */
    PlatformUser queryById(@Param(value = "id") Long id);

    /**
     * 通过登录用户名查询平台运营人员
     *
     * @param loginName 登录用户名
     * @return 运营人员对象
     */
    PlatformUser queryByLoginName(@Param(value = "loginName") String loginName);

    /**
     * 根据条件模糊查询平台运营人员列表，不包含管理员
     *
     * @param platformUserQB 查询参数
     * @return 平台运营人员
     */
    List<PlatformUser> queryUserList(PlatformUserQB platformUserQB);

    /**
     * 新增平台运营人员
     *
     * @param platformUser 平台运营人员
     * @return 操作结果，0-未成功，1-成功
     */
    int insertUser(PlatformUser platformUser);

    /**
     * 更新平台运营人员
     *
     * @param platformUser 平台运营人员
     * @return 操作结果，0-未成功，1-成功
     */
    int updateUser(PlatformUser platformUser);

}
