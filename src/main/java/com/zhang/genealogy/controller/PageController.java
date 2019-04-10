package com.zhang.genealogy.controller;

import com.zhang.genealogy.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面配置路径
 *
 * @Auther: zhangchao
 * @Date: 2019/3/15 10:42
 */

@Controller
public class PageController {

    /**
     * 登录页跳转
     *
     * @return
     */
    @RequestMapping("/")
    public String login() {
        return "redirect:" + Constants.PAGE_PATH + "login.html";
    }

    /**
     * 首页跳转
     *
     * @return
     */
    @RequestMapping("/zhang")
    public String zhang() {
        return "redirect:" + Constants.PAGE_PATH + "index.html";
    }
}
