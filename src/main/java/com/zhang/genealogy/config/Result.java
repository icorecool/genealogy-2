package com.zhang.genealogy.config;


import com.zhang.genealogy.constant.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口响应对象
 *
 * @author zhangchao
 * @date 2019-01-30
 */
public class Result {
    /**
     * 响应码
     */
    private String code = "0";

    /**
     * 响应消息
     */
    private String message = Constants.RESULT_OK_MESSAGE;

    /**
     * 响应数据
     */
    private Map<String, Object> data = new HashMap<>();

    /**
     * 增加结果数据到数据Map中
     *
     * @param key
     * @param value
     */
    public void addObject(String key, Object value) {
        data.put(key, value);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
