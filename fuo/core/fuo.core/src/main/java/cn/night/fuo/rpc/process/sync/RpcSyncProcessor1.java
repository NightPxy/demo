package cn.night.fuo.rpc.process.sync;

import cn.night.fuo.rpc.RpcSyncContext;
import cn.night.fuo.rpc.handlers.RpcProcessHandler1;

import java.util.function.Consumer;

/**
 * Created by Night on 2020/2/24.
 */
public class RpcSyncProcessor1<T, R> extends RpcSyncProcessor<R> {

    private RpcProcessHandler1<T, R> handler;

    private RpcSyncContext<R> context;

    private String traceId;

    public RpcSyncProcessor1(RpcSyncContext<R> context, RpcProcessHandler1<T, R> handler) {
        this.context = context;
        this.handler = handler;
    }

    public RpcSyncProcessor1<T, R> name(String processName) {
        this.processName = processName;
        return this;
    }

    public RpcSyncProcessor1<T, R> trace(String traceId) {
        this.traceId = traceId;
        return this;
    }

    private T t1;

    public RpcSyncContext<R> apply(T t1) {
        this.t1 = t1;
        return this.context;

    }

    @Override
    public R executeRpc() {
        String traceId = "";
        try {
            log.info(() -> "trace:" + traceId + this.processName+ " 发送请求:{}" + this.t1.toString());
            R response = this.handler.call(t1);
            log.info(() -> "trace:" + traceId + this.processName+ " 接受响应:{}" + response.toString());
            return response;
        } catch (Exception e) {
            log.info(e, () -> "trace:" + traceId + " 发生异常");
            return  null;
        }
    }
}
