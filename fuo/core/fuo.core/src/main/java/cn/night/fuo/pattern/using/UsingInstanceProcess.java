package cn.night.fuo.pattern.using;

/**
 * Created by Night on 2020/2/24.
 */
@FunctionalInterface
public interface UsingInstanceProcess<T,R> {
    R process(T context) throws Exception;
}
