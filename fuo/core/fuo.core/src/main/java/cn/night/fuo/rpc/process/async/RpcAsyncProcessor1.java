package cn.night.fuo.rpc.process.async;

import cn.night.fuo.rpc.handlers.RpcProcessHandler1;

import java.util.function.Consumer;

/**
 * Created by Night on 2020/2/24.
 */
public class RpcAsyncProcessor1<T, R> extends RpcAsyncProcessor<R> {

    private RpcProcessHandler1<T, R> handler;

    public RpcAsyncProcessor1(RpcAsyncContext context, RpcProcessHandler1<T, R> handler) {
        super(context);
        this.handler = handler;
    }

    public RpcAsyncProcessor1<T, R> name(String processName) {
        this.processName = processName;
        return this;
    }

    private T t1;

    public RpcAsyncProcessor1<T, R> apply(T t1) {
        this.t1 = t1;
        return this;
    }

    public RpcAsyncProcessor1<T, R> callback(Consumer<R> callback){
        this.processCallback = callback;
        return this;
    }

    public RpcAsyncProcessor1<T, R> trace(String trace){
        return this;
    }

    @Override
    void executeRpc() {
        String traceId = "";
        try {
            log.info(() -> "trace:" + traceId + this.processName+ " 发送请求:{}" + this.t1.toString());
            R response = this.handler.call(t1);
            log.info(() -> "trace:" + traceId + this.processName+ " 接受响应:{}" + response.toString());
            if (this.processCallback != null) {
                this.processCallback.accept(response);
            }
        } catch (Exception e) {
            log.info(e, () -> "trace:" + traceId + " 发生异常");
        }
    }
}
