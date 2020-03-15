package cn.night.fuo.rpc.jsf2.consumer;

import cn.night.fuo.rpc.jsf2.consumer.call.JsfCallOfRequest;

import java.util.function.Function;

/**
 * Created by Night on 2020/2/16.
 */
public class Jsf {
    //todo 杰夫模块设计
    public static <T, R> JsfCallOfRequest<T, R> call(Function<T, R> call) {
        return new JsfCallOfRequest<T, R>(call);
    }
}
