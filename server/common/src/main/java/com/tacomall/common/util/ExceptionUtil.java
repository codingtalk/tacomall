package com.tacomall.common.util;

import com.tacomall.common.exceptionInterceptor.exception.*;

public class ExceptionUtil {
    public static void throwBizException(String message) throws BizException {
        throw new BizException(message);
    }

    public static void throwClientException(String message) throws ClientException {
        throw new ClientException(message);
    }

    public static void throwServerException(String message) throws ServerException {
        throw new ServerException(message);
    }

    public static void throwSqlException(String message) throws SqlException {
        throw new SqlException(message);
    }

    public static void throwUnauthorizedException(String message) throws UnauthorizedException {
        throw new UnauthorizedException(message);
    }
}
