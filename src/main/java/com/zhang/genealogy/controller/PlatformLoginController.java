package com.zhang.genealogy.controller;

import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.dto.Result;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import com.zhang.genealogy.model.PlatformUser;
import com.zhang.genealogy.service.PlatformLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 平台用户登录对外接口
 *
 * @Auther: zhangchao
 * @Date: 2019/2/15 13:05
 */
@Controller
@RequestMapping("/platformUser")
public class PlatformLoginController {

    private static final Logger logger = LoggerFactory.getLogger(PlatformLoginController.class);

    /**
     * 平台用户登录服务类
     */
    @Resource
    private PlatformLoginService platformLoginService;

    /**
     * 平台用户登录
     *
     * @param request   请求
     * @param loginName 登录名
     * @param password  密码
     * @param code      验证码
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(HttpServletRequest request, String loginName, String password) {
        //参数不正确
        if (null == loginName) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY, "登录名");
        }
        if (null == password) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY, "密码");
        }

        HttpSession session = request.getSession();
        //登录
        PlatformUser platformUser = platformLoginService.login(loginName, password);

        // 校验通过时，在session里放入一个标识
        // 后续通过session里是否存在该标识来判断用户是否登录
        session.setAttribute(Constants.PLATFORM_USER_LOGIN_NAME, loginName);
        session.setAttribute(Constants.PLATFORM_USER_ID, platformUser.getId());
        session.setAttribute(Constants.PLATFORM_USER_TYPE, platformUser.getUserType());
        session.setAttribute(Constants.PLATFORM_USER_SHOW_NAME, platformUser.getUserShowName());

        Result resultout = new Result();
        resultout.addObject("platformUser", platformUser);
        return resultout;
    }


    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:" + Constants.PAGE_PATH + "login.html";
    }

}
