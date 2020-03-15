package cn.night.fuo.rpc.jsf2.consumer.call;

import cn.night.fuo.log.LogFacade;
import cn.night.fuo.spring.SpringContextHolder;

import java.util.function.Function;

/**
 * Created by Night on 2020/2/16.
 */
public class JsfCallOfRequest<T, R> {

    private LogFacade log = SpringContextHolder.getBean(LogFacade.class);

    private Function<T, R> call;

    public JsfCallOfRequest(Function<T, R> call) {
        this.call = call;
    }

    private String traceId;
    private String titleString;

    public JsfCallOfRequest<T, R> title(String title) {
        this.titleString = title;
        return this;
    }

    public JsfCallOfRequest<T, R> trace(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public R of(T request) {
        Class<?> clazz = call.getClass();
        try {
            log.info(() -> "trace:" + traceId + " 发送请求:{}" + request.toString());
            R result = call.apply(request);
            log.info(() -> "trace:" + traceId + " 接受响应:{}" + result.toString());
            return result;
        } catch (Exception e) {
            log.info(e, () -> "trace:" + traceId + " 发生异常");
        }
        return null;
    }
}
