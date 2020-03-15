package cn.night.fuo.pattern.using;

/**
 * Created by Night on 2020/2/24.
 */
@FunctionalInterface
public interface UsingInstanceConsumer<T> {
    void consume(T context) throws Exception;
}
