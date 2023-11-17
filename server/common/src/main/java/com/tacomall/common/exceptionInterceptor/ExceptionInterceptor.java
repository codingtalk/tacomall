
package com.tacomall.common.exceptionInterceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tacomall.common.enumeration.BizEnum;
import com.tacomall.common.enumeration.ExceptionEnum;
import com.tacomall.common.exceptionInterceptor.exception.*;
import com.tacomall.common.json.ResponseJson;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseJson<String> bizErrorHandler(HttpServletRequest req, BizException e) throws Exception {
        ResponseJson<String> bizError = new ResponseJson<>();
        bizError.setStatus(false);
        bizError.setCode(BizEnum.ERROR.getCode());
        bizError.setMessage(e.getMessage());
        return bizError;
    }

    @ExceptionHandler(value = SqlException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseJson<String> sqlErrorHandler(HttpServletRequest req, SqlException e) throws Exception {
        ResponseJson<String> sqlError = new ResponseJson<>();
        sqlError.setStatus(false);
        sqlError.setCode(ExceptionEnum.SERVER_ERROR.getCode());
        sqlError.setMessage(e.getMessage());
        return sqlError;
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseJson<String> unauthorizedErrorHandler(HttpServletRequest req, UnauthorizedException e)
            throws Exception {
        ResponseJson<String> unauthorizedError = new ResponseJson<>();
        unauthorizedError.setStatus(false);
        unauthorizedError.setCode(ExceptionEnum.UNAUTHORIZED_ERROR.getCode());
        unauthorizedError.setMessage(e.getMessage());
        return unauthorizedError;
    }
}
