package com.zhang.genealogy.exception;


import cn.hutool.core.util.StrUtil;

/**
 * 通用异常类
 *
 * @author zhangchao
 * @date 2019-01-30
 */
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 5580782278226784111L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 构造异常对象，消息中不包含参数时使用
     *
     * @param errorCode
     */
    public CommonException(ErrorCode errorCode) {
        super(errorCode.toString());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * 构造异常对象，消息中包含参数时使用
     *
     * @param errorCode
     * @param params    消息参数
     */
    public CommonException(ErrorCode errorCode, String... params) {
        super(errorCode.toString(replaceParam(errorCode.getMessage(), params)));
        this.code = errorCode.getCode();
        this.message = replaceParam(errorCode.getMessage(), params);
    }

    /**
     * 检查异常消息中是否包含参数，如果包含，则使用传入的参数值循环替换
     *
     * @param message 异常消息
     * @param params  异常消息参数
     * @return
     */
    private static String replaceParam(String message, String... params) {
        String errorMsg = message;
        if (StrUtil.isNotEmpty(errorMsg) && errorMsg.contains("{")
                && errorMsg.contains("}")) {
            // 每次循环替换一个参数
            for (String param : params) {
                String prefix = errorMsg.substring(0, errorMsg.indexOf("{"));
                String postfix = errorMsg.substring(errorMsg.indexOf("}") + 1);
                errorMsg = prefix + param + postfix;
            }
        }
        return errorMsg;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
