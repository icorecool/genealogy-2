package com.zhang.genealogy.controller;

import com.zhang.genealogy.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 方法1：Spring Boot 将所有的错误默认映射到/error， 实现ErrorController
 *
 * @Auther: zhangchao
 * @Date: 2019/4/15 13:20
 */
@Controller
@RequestMapping("/error")
public class ErrorPageController implements ErrorController {

    Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @Override
    public String getErrorPath() {
        return "redirect:" + Constants.PAGE_PATH + "error.html";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}