package cn.night.fuo.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * Created by Night on 2020/2/16.
 */
@Component("fuo.log.IFuoLog")
public interface LogFacade {

    void trace(String format, Object... arguments);

    void trace(Supplier<String> command);

    void trace(Throwable throwable, Supplier<String> command);

    void debug(String format, Object... arguments);

    void debug(Supplier<String> command);

    void debug(Throwable throwable, Supplier<String> command);

    void info(String format, Object... arguments);

    void info(Supplier<String> command);

    void info(Throwable throwable, Supplier<String> command);

    void warn(String format, Object... arguments);

    void warn(Supplier<String> command);

    void warn(Throwable throwable, Supplier<String> command);

    void error(String format, Object... arguments);

    void error(Throwable throwable,String format, Object... arguments);

    void error(Throwable throwable, String msg);

    void error(Supplier<String> command);

    void error(Throwable throwable, Supplier<String> command);
}
