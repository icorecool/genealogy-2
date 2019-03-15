package com.zhang.genealogy.exception;

import com.zhang.genealogy.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常统一处理类
 *
 * @author zhangchao
 * @date 2019-01-30
 */
@ControllerAdvice
public class CommonExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * 统一异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        Result result = new Result();
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            result.setCode(commonException.getCode());
            result.setMessage(commonException.getMessage());
            logger.error("自定义异常", commonException);
        } else {
            result.setCode(ErrorCode.UNKNOW_EXCEPTION.getCode());
            result.setMessage("操作失败");
            logger.error("未知异常", e);
        }
        return result;
    }

} 