package cn.night.fuo.exception.check;

/**
 * Created by Night on 2020/2/24.
 */
public class FuoCheckException extends Exception {
    public FuoCheckException(String message) {
        super(message);
    }

    public FuoCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
