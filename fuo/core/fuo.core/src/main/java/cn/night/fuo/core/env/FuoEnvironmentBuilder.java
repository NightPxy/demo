package cn.night.fuo.core.env;

import cn.night.fuo.exception.check.FuoEnvironmentInitializeFailedException;

/**
 * Created by Night on 2020/2/20.
 */
public abstract class FuoEnvironmentBuilder<T> {
    public abstract T doBuild(FuoEnvironmentContext context) throws FuoEnvironmentInitializeFailedException;

    public T build(FuoEnvironmentContext context) throws FuoEnvironmentInitializeFailedException {
        try {
            return this.doBuild(context);
        } catch (Exception e) {
            throw new FuoEnvironmentInitializeFailedException(e.getMessage());
        }
    }
}
