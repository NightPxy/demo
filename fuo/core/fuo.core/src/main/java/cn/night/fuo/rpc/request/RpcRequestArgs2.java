package cn.night.fuo.rpc.request;

import lombok.Data;

/**
 * Created by Night on 2020/2/24.
 */
@Data
public class RpcRequestArgs2<T1,T2> {
    private T1 t1;
    private T2 t2;
}
