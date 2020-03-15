package cn.night.fuo.rpc.handlers;

/**
 * Created by Night on 2020/2/24.
 */
@FunctionalInterface
public interface RpcProcessHandler2<T1, T2, R> {
    default R process2(T1 t, T2 t2) throws Exception {
        return this.process(t, t2);
    }

    R process(T1 t, T2 t2) throws Exception;
}
