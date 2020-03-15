package cn.night.fuo.rpc.handlers;

/**
 * Created by Night on 2020/2/24.
 */
@FunctionalInterface
public interface RpcProcessHandler3<T1, T2, T3, R> {
    R process(T1 t, T2 t2, T3 t3);
}
