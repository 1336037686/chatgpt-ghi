package com.ghi.common.exception.handler;

import com.ghi.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * 请求参数异常处理
 * 业务异常处理
 * 系统异常处理
 * @author LGX_TvT <br>
 * @version 1.0 <br>
 * Create by 2022-04-05 02:15 <br>
 * @description: GlobalExceptionHandler <br>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(Throwable e){
        // 打印堆栈信息
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Result-Message", e.getMessage())
                .build();
    }

    /**
     * 处理所有接口数据验证异常
     * 需要验证的参数上加上了@Valid注解，如果验证失败，它将抛出MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error(e.getMessage());
        List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        List<String> msgList = fieldErrors.stream().map(FieldError :: getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Result-Message", String.join(",", msgList))
                .build();
    }

    /**
     * 处理所有接口业务异常
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException e){
        // 打印堆栈信息
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Result-Message", e.getMessage())
                .build();
    }

}
