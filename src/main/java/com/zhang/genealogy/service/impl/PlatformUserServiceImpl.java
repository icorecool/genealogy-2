package com.zhang.genealogy.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.zhang.genealogy.dao.PlatformUserDAO;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import com.zhang.genealogy.model.PlatformUser;
import com.zhang.genealogy.service.PlatformUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 平台运营人员服务实现
 *
 * @author zhangchao
 * @date 2019-01-29
 */
@Service
public class PlatformUserServiceImpl implements PlatformUserService {

    /**
     * 平台运营人员数据持久化对象
     */
    @Resource
    private PlatformUserDAO platformUserDAO;

    /**
     * 通过编号查询平台运营人员
     *
     * @param id 运营人员编号
     * @return 运营人员对象
     */
    @Override
    public PlatformUser queryUserById(Long id) {
        if (id == null) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY, "运营人员编号");
        }
        return platformUserDAO.queryById(id);
    }

    /**
     * 通过登录用户名查询平台运营人员
     *
     * @param loginName 登录用户名
     * @return 运营人员对象
     */
    @Override
    public PlatformUser queryByLoginName(String loginName) {
        if (StrUtil.isEmpty(loginName)) {
            throw new CommonException(ErrorCode.PARAM_IS_INVALID,
                    "登录用户名" + Optional.ofNullable(loginName).orElse("null"));
        }
        return platformUserDAO.queryByLoginName(loginName);
    }

    /**
     * 更新平台运营人员
     *
     * @param platformUser 平台运营人员
     * @return 操作结果，0-未成功，1-成功
     */
    @Override
    public int modifyUser(PlatformUser platformUser) {
        platformUser.setUpdateTime(DateUtil.date());
        return platformUserDAO.updateUser(platformUser);
    }

    /**
     * 修改平台运营人员密码
     *
     * @param id       平台运营人员主键
     * @param password 新密码
     * @return
     */
    @Override
    public int changeUserPassword(Long id, String password) {
        //校验登录名是否重复
        PlatformUser searchUser = queryUserById(id);
        if (null == searchUser) {
            throw new CommonException(ErrorCode.RECORD_IS_EXISTS, "平台运营人员" + id);
        }
        String realPassword = password2MD5(searchUser.getLoginName(), password);
        PlatformUser platformUser = new PlatformUser();
        platformUser.setId(id);
        platformUser.setPassword(realPassword);
        return modifyUser(platformUser);
    }

    /**
     * 获取密码 = MD5(登录名+密码)
     *
     * @param loginName 登录名
     * @param password  密码
     * @return
     */
    private String password2MD5(String loginName, String password) {
        String md5info = loginName + password;
        return SecureUtil.md5(md5info);
    }

}
