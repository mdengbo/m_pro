package com.m.commons.web.exception;

import com.m.commons.web.utils.RequestUtils;
import com.m.data.r.CodesEnums;
import com.m.data.r.R;
import com.m.data.r.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义统一异常处理
 * */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 校验入参统一异常处理
     * */
    @ExceptionHandler(BindException.class)
    public R bindExceptionHandler(BindException e){

        log.error("[bind exception] 捕获异常", e);
        Set<Object> errs = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());

        return RUtils.create(CodesEnums.PARAM_ERROR, errs);
    }

    /**
     * http 校验入参统一异常处理
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R getHttpMethodArgumentNotValidException(MethodArgumentNotValidException e){

        log.error("[http exception] 捕获异常", e);
        Set<Object> errs = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());


        return RUtils.create(CodesEnums.PARAM_ERROR, errs);
    }

    /**
     * 基于形参（get 请求校验）
     * */
    @ExceptionHandler(ConstraintViolationException.class)
    public R getConstraintViolationException(ConstraintViolationException e){

        log.error("[http exception] 捕获异常", e);
        Set<Object> errs = e.getConstraintViolations().stream()
                .map(err-> err.getMessage())
                .collect(Collectors.toSet());
        return RUtils.create(CodesEnums.PARAM_ERROR, errs);
    }

    /**
     * 统一异常处理
     * */
    @ExceptionHandler(Throwable.class)
    public R exceptionHandler(Throwable t){

        try{

            //获取当前请求的url
            HttpServletRequest request = RequestUtils.getHttpServletRequest();

            //获取请求的url
            if(null != request) {
                String uri = request.getRequestURL().toString();
                log.error("[global exception] 捕获异常, uri：" + uri, t);
            }

        }catch (Exception e) {
            log.error("[global exception] 捕获异常", t);
        }

        return RUtils.fail("交易异常:" + t.getMessage());
    }


}
