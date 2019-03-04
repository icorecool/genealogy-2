package com.zhang.genealogy.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 登录拦截器注册类
 *
 * @author zhangchao
 * @date 2019-02-15
 */
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

    /**
     * 注册拦截器
     *
     * @param registry 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/platformUser/getVerify");
        loginRegistry.excludePathPatterns("/platformUser/checkVerify");
        loginRegistry.excludePathPatterns("/platformUser/login");
        loginRegistry.excludePathPatterns("/platformUser/loginout");

    }
}
