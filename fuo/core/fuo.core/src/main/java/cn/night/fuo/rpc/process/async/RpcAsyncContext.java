package cn.night.fuo.rpc.process.async;

import cn.night.fuo.rpc.RpcContext;
import cn.night.fuo.rpc.handlers.RpcProcessHandler1;
import cn.night.fuo.rpc.handlers.RpcProcessHandler2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Night on 2020/2/24.
 */
public class RpcAsyncContext extends RpcContext {

    private List<RpcAsyncProcessor> calls = new ArrayList<>();

    public <T1, R> RpcAsyncProcessor1<T1, R> call(RpcProcessHandler1<T1, R> handler) throws Exception {
        RpcAsyncProcessor1 rpcProcessor = new RpcAsyncProcessor1<T1, R>(this, handler);
        calls.add(rpcProcessor);
        return rpcProcessor;
    }

    public <T1, T2, R> RpcAsyncProcessor2<T1, T2, R> call(RpcProcessHandler2<T1, T2, R> handler) {
        RpcAsyncProcessor2 rpcProcessor = new RpcAsyncProcessor2<T1, T2, R>(this, handler);
        calls.add(rpcProcessor);
        return rpcProcessor;
    }

    public void awaitResponse() {
        for (RpcAsyncProcessor process : calls) {
            process.executeRpc();
        }
    }
}
