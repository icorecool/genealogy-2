package com.zhang.genealogy.controller;

import com.zhang.genealogy.config.Result;
import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import com.zhang.genealogy.model.PlatformUser;
import com.zhang.genealogy.service.PlatformLoginService;
import com.zhang.genealogy.util.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
    public Result login(HttpServletRequest request, String loginName, String password, String code) {
        //参数不正确
        if (null == loginName) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY, "登录名");
        }
        if (null == password) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY, "密码");
        }
        if (null == code) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY, "验证码");
        }

        HttpSession session = request.getSession();
        //获取session中的值
        Object verCode = session.getAttribute("verCode");
        LocalDateTime localDateTime = (LocalDateTime) session.getAttribute("codeTime");
        long generationTime;
        try {
            generationTime = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        } catch (Exception e) {
            throw new CommonException(ErrorCode.USER_VERIFY_ERROR);
        }
        //校验验证码
        platformLoginService.checkVerify(code, verCode.toString(), generationTime);

        //登录
        PlatformUser platformUser = platformLoginService.login(loginName, password);

        // 校验通过时，在session里放入一个标识
        // 后续通过session里是否存在该标识来判断用户是否登录
        session.setAttribute(Constants.PLATFORM_USER_LOGIN_NAME, loginName);
        session.setAttribute(Constants.PLATFORM_USER_ID, platformUser.getId());
        session.setAttribute(Constants.PLATFORM_USER_TYPE, platformUser.getUserType());
        session.setAttribute(Constants.PLATFORM_USER_SHOW_NAME, platformUser.getUserShowName());
        // 验证成功，删除存储的验证码
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");

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
    @ResponseBody
    public Result loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Result();
    }


    /**
     * 生成验证码
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4).toUpperCase();
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");
        // 设置新的
        session.setAttribute("verCode", verifyCode);
        session.setAttribute("codeTime", LocalDateTime.now());
        // 生成图片
        int w = 100, h = 40;
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            VerifyCodeUtils.outputImage(w, h, out, verifyCode);
        } catch (IOException e) {
            logger.error("验证码错误:", e);
            throw new CommonException(ErrorCode.USER_VERIFY_FAIL);
        }
    }

    /**
     * 校验验证码
     *
     * @param code    验证码
     * @param session
     * @return
     */
    @RequestMapping(value = "/checkVerify")
    @ResponseBody
    public Result checkVerify(String code, HttpSession session) {

        //获取session中的值
        Object verCode = session.getAttribute("verCode");
        LocalDateTime localDateTime = (LocalDateTime) session.getAttribute("codeTime");
        long generationTime = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        //校验
        platformLoginService.checkVerify(code, verCode.toString(), generationTime);
        return new Result();
    }

}
