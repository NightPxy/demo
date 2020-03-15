package cn.night.fuo.rpc;

import cn.night.fuo.log.LogFacade;
import cn.night.fuo.rpc.handlers.RpcProcessHandler2;
import cn.night.fuo.spring.SpringContextHolder;
import lombok.Getter;

/**
 * Created by Night on 2020/2/24.
 */
@Getter
public abstract class RpcContext {

    protected String tradeId;

    private LogFacade log = SpringContextHolder.getBean(LogFacade.class);

}
