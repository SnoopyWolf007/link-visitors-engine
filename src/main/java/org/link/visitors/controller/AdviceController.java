package org.link.visitors.controller;

import lombok.extern.slf4j.Slf4j;
import org.link.visitors.core.dto.IResponse;
import org.link.visitors.core.exception.BizException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ginger
 */
@Slf4j
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler
    public Object handleException(Exception e) {
        log.error("unhandled controller exception", e);
        if (e instanceof BizException) {
            BizException err = (BizException) e;
            return IResponse.getInstance().code(err.getCode()).message(err.getMessage());
        }
        return IResponse.getInstance().fail().message("服务器内部异常");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return IResponse.getInstance().code(500).message("参数不正确");
    }

}
