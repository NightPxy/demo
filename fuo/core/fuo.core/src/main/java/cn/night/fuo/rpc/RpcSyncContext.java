package cn.night.fuo.rpc;

import cn.night.fuo.rpc.handlers.RpcProcessHandler1;
import cn.night.fuo.rpc.process.sync.RpcSyncProcessor;
import cn.night.fuo.rpc.process.sync.RpcSyncProcessor1;

/**
 * Created by Night on 2020/2/24.
 */
public class RpcSyncContext<R> extends RpcContext {

    public RpcSyncContext<R> trace(String tradeId) {
        this.tradeId = tradeId;
        return this;
    }

    private RpcSyncProcessor<R> processor;

    public <T1> RpcSyncProcessor1<T1, R> rpcCall(RpcProcessHandler1<T1, R> process) {
        RpcSyncProcessor1<T1, R> rpcProcess = new RpcSyncProcessor1(this, process);
        this.processor = rpcProcess;
        return rpcProcess;
    }

    public R response(){
        return this.processor.executeRpc();
    }

}
