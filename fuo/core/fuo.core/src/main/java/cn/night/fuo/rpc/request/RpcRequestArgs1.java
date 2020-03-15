package cn.night.fuo.rpc.request;

import lombok.Data;

/**
 * Created by Night on 2020/2/24.
 */
@Data
public class RpcRequestArgs1<T1> {
    private T1 t1;
}
