package cn.night.fuo.rpc.process.sync;

import cn.night.fuo.log.LogFacade;
import cn.night.fuo.spring.SpringContextHolder;
import lombok.Getter;

/**
 * Created by Night on 2020/2/25.
 */
@Getter
public abstract class RpcSyncProcessor<R> {

    protected LogFacade log = SpringContextHolder.getBean(LogFacade.class);

    protected String processName;

    public abstract R executeRpc();
}
