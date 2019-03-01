package com.zhang.genealogy.service.impl;

import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import com.zhang.genealogy.model.PlatformUser;
import com.zhang.genealogy.service.PlatformLoginService;
import com.zhang.genealogy.service.PlatformUserService;
import com.zhang.genealogy.util.DateUtil;
import com.zhang.genealogy.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 平台用户登录服务实现类
 *
 * @Auther: zhangchao
 * @Date: 2019/2/15 13:43
 */
@Service
public class PlatformLoginServiceImpl implements PlatformLoginService {

    /**
     * 平台运营人员服务接口
     */
    @Resource
    private PlatformUserService platformUserService;

    /**
     * 平台用户登录
     *
     * @param loginName 登录名
     * @param password  密码
     * @return
     */
    @Override
    public PlatformUser login(String loginName, String password) {
        //校验用户名密码
        PlatformUser platformUser = platformUserService.queryByLoginName(loginName);
        if (null == platformUser) {
            throw new CommonException(ErrorCode.USERNAME_NOT_EXIST);
        }
        if (Constants.STATUS_PROHIBIT == platformUser.getUserStatus()) {
            throw new CommonException(ErrorCode.USER_STATUS_PROHIBIT);
        }
        //密码 = MD5(用户名+密码)
        String md5info = loginName + password;
        String realPassword = MD5Util.toMD5(md5info);
        if (!realPassword.equals(platformUser.getPassword())) {
            throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
        }

        //更新登录时间
        platformUser.setLoginTime(DateUtil.getCurrentDate());
        platformUserService.modifyUser(platformUser);
        //敏感字段处理
        platformUser.setPassword(null);
        return platformUser;
    }

    /**
     * 校验验证码
     *
     * @param code           web传输的验证码
     * @param verCode        session中的验证码
     * @param generationTime 验证码生成时间
     */
    @Override
    public void checkVerify(String code, String verCode, long generationTime) {
        if (null == verCode) {
            throw new CommonException(ErrorCode.USER_VERIFY_ERROR);
        }
        String verCodeStr = verCode.toUpperCase();
        long now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        //验证失败
        if (verCodeStr == null || code == null || code.isEmpty() || !verCodeStr.equalsIgnoreCase(code)) {
            throw new CommonException(ErrorCode.USER_VERIFY_FAIL);
        } else if ((now - generationTime) / 1000 / 60 > 5) {
            //5分钟之前验证码无效
            throw new CommonException(ErrorCode.USER_VERIFY_EXPIRED);
        }
    }
}
