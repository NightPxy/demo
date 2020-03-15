package cn.night.fuo.rpc.process.async;

import cn.night.fuo.log.LogFacade;
import cn.night.fuo.spring.SpringContextHolder;

import java.util.function.Consumer;

/**
 * Created by Night on 2020/2/25.
 */

public abstract class RpcAsyncProcessor<R> {
    protected LogFacade log = SpringContextHolder.getBean(LogFacade.class);
    protected RpcAsyncContext context;
    protected Consumer<R> processCallback;

    protected String processName;

    public RpcAsyncProcessor(RpcAsyncContext context) {
        this.context = context;
    }
    public void awaitResponse(){
        this.context.awaitResponse();
    }

    public RpcAsyncContext then(){
        return this.context;
    }



    abstract void executeRpc();
}
