package com.zhang.genealogy.config;


import cn.hutool.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 全局页面传入日期字符串，自动转换成日期格式
 *
 * @Auther: zhangchao
 * @Date: 2019/2/25 11:41
 */
@Component
public class DateConfig implements Converter<String, Date> {

    private static final List<String> formarts = new ArrayList<>(4);

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        } else {
            return DateUtil.parse(source);
        }
    }


}