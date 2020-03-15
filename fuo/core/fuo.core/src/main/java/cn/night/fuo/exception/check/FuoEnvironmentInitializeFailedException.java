package cn.night.fuo.exception.check;


/**
 * Created by Night on 2020/2/21.
 */
public class FuoEnvironmentInitializeFailedException extends FuoCheckException {
    public FuoEnvironmentInitializeFailedException(String msg){
        super(msg);
    }

    public FuoEnvironmentInitializeFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
