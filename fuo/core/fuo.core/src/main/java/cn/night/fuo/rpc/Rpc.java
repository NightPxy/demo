package cn.night.fuo.rpc;

import cn.night.fuo.rpc.handlers.RpcProcessHandler1;
import cn.night.fuo.rpc.process.async.RpcAsyncContext;
import cn.night.fuo.rpc.process.sync.RpcSyncProcessor1;

/**
 * Created by Night on 2020/2/24.
 */

public class Rpc {
    public static RpcContext call(){
        return null;
    }

    public static RpcSyncContext sincal(){
        return new RpcSyncContext();
    }

    public static <T,R> RpcSyncProcessor1<T, R> sincal(RpcProcessHandler1<T, R> process){
        return sincal().rpcCall(process);
    }


    public static RpcAsyncContext parallel(){
        return new RpcAsyncContext();
    }
}
