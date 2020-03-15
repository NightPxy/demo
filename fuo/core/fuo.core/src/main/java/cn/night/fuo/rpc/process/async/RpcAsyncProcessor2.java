package cn.night.fuo.rpc.process.async;

import cn.night.fuo.rpc.handlers.RpcProcessHandler1;
import cn.night.fuo.rpc.handlers.RpcProcessHandler2;

import java.util.function.Consumer;

/**
 * Created by Night on 2020/2/24.
 */
public class RpcAsyncProcessor2<T1,T2, R> extends RpcAsyncProcessor<R> {

    private RpcProcessHandler2<T1,T2, R> handler;

    public RpcAsyncProcessor2(RpcAsyncContext context, RpcProcessHandler2<T1,T2, R> handler) {
        super(context);
        this.handler = handler;
    }

    public RpcAsyncProcessor2<T1,T2, R> name(String processName) {
        this.processName = processName;
        return this;
    }

    private T1 t1;
    private T2 t2;

    public RpcAsyncProcessor2<T1,T2, R> apply(T1 t1,T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
        return this;
    }

    public RpcAsyncProcessor2<T1,T2, R> callback(Consumer<R> callback){
        this.processCallback = callback;
        return this;
    }

    @Override
    void executeRpc() {
        String traceId = "";
        try {
            log.info(() -> "trace:" + traceId + this.processName+ " 发送请求: arg1:" + this.t1.toString());
            R response = this.handler.process(t1,t2);
            log.info(() -> "trace:" + traceId + this.processName+ " 接受响应:{}" + response.toString());
            if (this.processCallback != null) {
                this.processCallback.accept(response);
            }
        } catch (Exception e) {
            log.info(e, () -> "trace:" + traceId + " 发生异常");
        }
    }
}
