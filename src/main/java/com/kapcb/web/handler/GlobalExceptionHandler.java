package com.kapcb.web.handler;

import cn.hutool.core.util.StrUtil;
import com.kapcb.web.exception.BusinessException;
import com.kapcb.web.model.result.CommonResult;
import kapcb.framework.web.constants.enums.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

/**
 * <a>Title: GlobalExceptionHandler </a>
 * <a>Author: Kapcb <a>
 * <a>Description: GlobalExceptionHandler <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 13:00
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    public CommonResult handBusinessException(BusinessException businessException) {
        log.error("hand BusinessException, error message is : {}", businessException.getMessage());
        return CommonResult.failed(businessException.getResultCode());
    }

    @ExceptionHandler(value = {BindException.class})
    public CommonResult handBindException(BindException bindException) {
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            stringBuilder.append(fieldError.getField()).append(fieldError.getDefaultMessage()).append(StringPool.SPACE_COMMA);
        }
        String message = subMessageComma(stringBuilder);
        log.error("hand BindException, error message is : {}", message);
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public CommonResult handConstraintViolationException(ConstraintViolationException constraintViolationException) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            Path propertyPath = constraintViolation.getPropertyPath();
            String[] pathPropertyArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(propertyPath.toString(), StringPool.DOT.value());
            stringBuilder.append(pathPropertyArray[1]).append(constraintViolation.getMessage()).append(StringPool.SPACE_COMMA);
        }
        String message = subMessageComma(stringBuilder);
        log.error("hand ConstraintViolationException, error message is : {}", message);
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public CommonResult handMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            stringBuilder.append(fieldError.getField()).append(fieldError.getDefaultMessage()).append(StringPool.SPACE_COMMA.value());
        }
        String message = subMessageComma(stringBuilder);
        log.error("hand MethodArgumentNotValidException, error message is : {}", message);
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public CommonResult handAccessDeniedException(AccessDeniedException accessDeniedException) {
        log.error("hand AccessDeniedException, error message is : {}", accessDeniedException.getMessage());
        return CommonResult.forbidden();
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public CommonResult handHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
        StringBuilder message = new StringBuilder();
        message.append("target method not support [")
                .append(StringUtils.substringBetween(httpMediaTypeNotSupportedException.getMessage(), StringPool.SINGLE_QUOTES.value(), StringPool.SINGLE_QUOTES.value()))
                .append("] media type");
        log.error("hand HttpMediaTypeNotSupportedException, error message is : {}", message.toString());
        return CommonResult.failed(message.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    public CommonResult handHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException httpMediaTypeNotAcceptableException) {
        StringBuilder message = new StringBuilder();
        message.append("target method not accept [")
                .append(StringUtils.substringBetween(httpMediaTypeNotAcceptableException.getMessage(), StringPool.SINGLE_QUOTES.value(), StringPool.SINGLE_QUOTES.value()))
                .append("] media type");
        log.error("hand HttpMediaTypeNotAcceptableException, error message is : {}", message.toString());
        return CommonResult.failed(message.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private static String subMessageComma(StringBuilder stringBuilder) {
        return StrUtil.sub(stringBuilder.toString(), 0, stringBuilder.length() - 1);
    }
}
