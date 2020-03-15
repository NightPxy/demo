package cn.night.fuo.rpc.request;

import lombok.Data;

/**
 * Created by Night on 2020/2/24.
 */
@Data
public class RpcRequestArgs4<T1,T2,T3,T4> {
    private T1 t1;
    private T2 t2;
    private T3 t3;
    private T4 t4;
}
