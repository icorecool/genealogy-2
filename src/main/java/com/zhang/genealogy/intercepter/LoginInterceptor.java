package com.zhang.genealogy.intercepter;

import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author zhangchao
 * @date 2019-02-15
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在请求被处理之前调用
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 检查每个到来的请求对应的session域中是否有登录标识
        Object loginName = request.getSession().getAttribute(Constants.PLATFORM_USER_LOGIN_NAME);
        if (null == loginName || !(loginName instanceof String)) {
            throw new CommonException(ErrorCode.USER_NOT_LOGIN);
        }
        return true;
    }

}
