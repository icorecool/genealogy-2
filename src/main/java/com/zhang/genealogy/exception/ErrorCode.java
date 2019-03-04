package com.zhang.genealogy.exception;

/**
 * 异常码和异常消息枚举
 *
 * @author zhanghcao
 * @date 2019-01-30
 */
public enum ErrorCode {
    // 未知异常
    UNKNOW_EXCEPTION("-2", "接口调用出错"),

    // 操作失败异常
    OPERATION_FAILED("-1", "{0}操作失败"),

    /*----------start: 通用异常 ------- */
    // 必填属性值为空异常
    PARAM_IS_EMPTY("10000", "[{0}]参数不能为空"),

    // 解析时间异常
    PARSE_DATE_ERROR("10001", "解析时间[{0}]出错"),

    // 加密数据出错
    ENCRYPT_DATA_ERROR("10002", "加密数据[{0}]出错"),

    // 解密数据出错
    DECRYPT_DATA("10003", "解密数据[{0}]出错"),

    // Http URL不存在异常
    HTTP_URL_NOT_EXISTS("10004", "请求的URL不存在：{0}"),

    // HTTP响应出错异常
    HTTP_RESPONSE_ERROR("10005", "HTTP响应出错，响应码：{0}"),

    // 发送HTTP请求出错异常
    SEND_HTTP_REQUEST_ERROR("10006", "发送HTTP请求出错"),

    // 参数传值不正确异常
    PARAM_IS_INVALID("10007", "参数[{0}]不正确"),

    // 记录已经存在异常
    RECORD_IS_EXISTS("10014", "{0}记录已经存在"),

    // 记录不存在异常
    RECORD_IS_NOT_EXISTS("10015", "{0}记录不存在"),

    // 属性值不能同时为空异常
    ALL_PARAM_IS_EMPTY("10016", "[{0}]参数同时为空"),

    // 不支持的文件类型异常
    UNSUPPORTED_FILE_TYPES("10017", "不支持该文件类型"),

    // 解析excel文件异常
    PARSE_EXCEL_ERROR("10018", "解析Excel文件出错"),

    // 编码数据异常
    ENCODER_DATA_ERROR("10019", "编码数据[{0}]出错"),

    // 导出Excel异常
    EXPORT_EXCEL_ERROR("10021", "导出数据到Excel出错"),

    // 参数校验失败异常
    PARAM_VALIDATE_FAILED("10023", "参数校验失败：{0}"),
    /*----------end: 通用异常 ------- */


    /*----------start:用户异常 ---------*/
    USER_VERIFY_ERROR("20001", "验证码已失效"),

    USER_VERIFY_FAIL("20002", "验证码错误"),

    USER_VERIFY_EXPIRED("20003", "验证码已过期"),

    USERNAME_NOT_EXIST("20005", "用户名不存在"),

    USER_PASSWORD_ERROR("20006", "密码不正确"),

    USER_NOT_LOGIN("20007", "用户未登录"),

    USER_STATUS_PROHIBIT("20008", "用户被禁止"),
    /*----------end:用户异常 ---------*/


    /*----------start:文件上传异常 ---------*/
    FILE_UPLOAD_ERROR("30001", "文件上传失败");
    /*----------end:文件上传异常 ---------*/
    /**
     * 错误码
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;


    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[" + this.code + "]" + this.message;
    }

    /**
     * 如果异常消息中有参数，需要替换后再使用
     *
     * @param message
     * @return
     */
    public String toString(String message) {
        return "[" + this.code + "]" + message;
    }

}
