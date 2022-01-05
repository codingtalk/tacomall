/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2022-01-05 14:03:27
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/exceptionInterceptor/ExceptionInterceptor.java
 * @Just do what I think it is right
 */
package store.tacomall.common.exceptionInterceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.enumeration.BizEnum;
import store.tacomall.common.enumeration.ExceptionEnum;
import store.tacomall.common.exceptionInterceptor.exception.*;

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
    public ResponseJson<String> unauthorizedHandler(HttpServletRequest req, UnauthorizedException e) throws Exception {
        ResponseJson<String> unauthorizedError = new ResponseJson<>();
        unauthorizedError.setStatus(false);
        unauthorizedError.setCode(ExceptionEnum.AUTHORIZED_ERROR.getCode());
        unauthorizedError.setMessage(e.getMessage());
        return unauthorizedError;
    }
}
