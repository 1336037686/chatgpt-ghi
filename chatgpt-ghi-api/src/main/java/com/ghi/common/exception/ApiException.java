package com.ghi.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 业务异常
 * @author LGX_TvT <br>
 * @version 1.0 <br>
 * Create by 2022-04-05 02:16 <br>
 * @description: BusinessException <br>
 */
@Data
public class ApiException extends RuntimeException {

    private Integer code;

    private String msg;

    public ApiException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiException(String message, Integer code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public ApiException(Throwable cause, Integer code, String msg) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public ApiException(HttpStatus status) {
        this.code = status.value();
        this.msg = status.getReasonPhrase();
    }

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.code = status.value();
        this.msg = message;
    }

    public ApiException(Throwable cause, HttpStatus status) {
        super(status.getReasonPhrase(), cause);
        this.code = status.value();
        this.msg = status.getReasonPhrase();
    }

}
