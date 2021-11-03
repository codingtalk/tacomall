package store.tacomall.common.exceptionInterceptor.exception;

public class SqlException extends RuntimeException {
  public SqlException(String message) {
    super(message);
  }
}
