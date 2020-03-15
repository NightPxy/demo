package cn.night.fuo.exception.runtime;

/**
 * Created by Night on 2020/2/24.
 */
public class FuoIllegalArgumenRuntimetException extends FuoRuntimeException {
    public FuoIllegalArgumenRuntimetException(String msg) {
        super(msg);
    }

    public FuoIllegalArgumenRuntimetException(String message, Throwable cause) {
        super(message, cause);
    }
}
