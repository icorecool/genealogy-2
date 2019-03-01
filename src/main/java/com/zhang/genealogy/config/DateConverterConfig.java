package com.zhang.genealogy.config;


import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import com.zhang.genealogy.util.DateUtil;
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
public class DateConverterConfig implements Converter<String, Date> {

    private static final List<String> formarts = new ArrayList<>(4);

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            //"yyyy-MM"
            return DateUtil.str2Date(source, DateUtil.DEFAULT_DATE_PATTERN_YEAR_MONTH);
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            //"yyyy-MM-dd"
            return DateUtil.str2Date(source, DateUtil.DEFAULT_DATE_PATTERN);
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            //"yyyy-MM-dd hh:mm"
            return DateUtil.str2Date(source, DateUtil.DEFAULT_DATE_PATTERN_MINUTE);
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            //"yyyy-MM-dd hh:mm:ss"
            return DateUtil.str2Date(source, DateUtil.DEFAULT_DATE_PATTERN_DETAIL);
        } else {
            throw new CommonException(ErrorCode.PARSE_DATE_ERROR, source);
        }
    }


}