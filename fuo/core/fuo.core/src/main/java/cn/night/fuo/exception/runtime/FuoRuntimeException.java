package cn.night.fuo.exception.runtime;

/**
 * Created by Night on 2020/2/24.
 */
public class FuoRuntimeException extends RuntimeException {
    public FuoRuntimeException(String msg) {
        super(msg);
    }

    public FuoRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
