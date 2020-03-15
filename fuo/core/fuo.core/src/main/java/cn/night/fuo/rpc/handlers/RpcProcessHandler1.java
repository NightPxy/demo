package cn.night.fuo.rpc.handlers;

/**
 * Created by Night on 2020/2/24.
 */
@FunctionalInterface
public interface RpcProcessHandler1<T1,R> {
    R call(T1 t);
}
