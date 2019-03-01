package com.zhang.genealogy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.dao.PlatformUserDAO;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import com.zhang.genealogy.model.PlatformUser;
import com.zhang.genealogy.qb.PlatformUserQB;
import com.zhang.genealogy.service.PlatformUserService;
import com.zhang.genealogy.util.CommonUtil;
import com.zhang.genealogy.util.DateUtil;
import com.zhang.genealogy.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 平台运营人员服务实现
 *
 * @author wuq
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
        if (id < 1) {
            throw new CommonException(ErrorCode.PARAM_IS_INVALID, "运营人员编号" + id);
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
        if (CommonUtil.isEmpty(loginName)) {
            throw new CommonException(ErrorCode.PARAM_IS_INVALID,
                    "登录用户名" + Optional.ofNullable(loginName).orElse("null"));
        }
        return platformUserDAO.queryByLoginName(loginName);
    }

    /**
     * 分页查询平台运营人员列表，不包含管理员
     *
     * @param pageNum    第几页编号
     * @param pageSize   页面大小
     * @param queryParam 查询参数
     * @return 平台运营人员
     */
    @Override
    public PageInfo<PlatformUser> queryUserPage(Integer pageNum, Integer pageSize, String queryParam) {
        // 分页参数校验
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageNum < 1) {
            pageSize = 10;
        }
        // 启用分页
        PageHelper.startPage(pageNum, pageSize);
        // 包装查询条件
        PlatformUserQB platformUserQB = new PlatformUserQB();
        platformUserQB.setQueryParam(queryParam);
        platformUserQB.setUserType(Constants.CUST_TYPE_ORDINARY);
        // 分页查询平台用户数据
        List<PlatformUser> userList = platformUserDAO.queryUserList(platformUserQB);
        //密码处理
        for (PlatformUser user : userList) {
            user.setPassword(null);
        }
        // 构造分页对象
        PageInfo<PlatformUser> userPage = new PageInfo<>(userList);
        return userPage;
    }

    /**
     * 新增平台运营人员
     *
     * @param platformUser 平台运营人员
     * @return 操作结果，0-未成功，1-成功
     */
    @Override
    public int addUser(PlatformUser platformUser) {
        //校验登录名是否重复
        PlatformUser searchUser = queryByLoginName(platformUser.getLoginName());
        if (null != searchUser) {
            throw new CommonException(ErrorCode.RECORD_IS_EXISTS, "登录名" + platformUser.getLoginName());
        }
        //密码 = MD5(用户名+密码)
        String realPassword = password2MD5(platformUser.getLoginName(), platformUser.getPassword());
        platformUser.setPassword(realPassword);
        platformUser.setCreateTime(DateUtil.getCurrentDate());
        platformUser.setUpdateTime(DateUtil.getCurrentDate());
        platformUser.setUserStatus(Constants.STATUS_NORMAL);
        return platformUserDAO.insertUser(platformUser);
    }

    /**
     * 更新平台运营人员
     *
     * @param platformUser 平台运营人员
     * @return 操作结果，0-未成功，1-成功
     */
    @Override
    public int modifyUser(PlatformUser platformUser) {
        platformUser.setUpdateTime(DateUtil.getCurrentDate());
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
        return MD5Util.toMD5(md5info);
    }

}
