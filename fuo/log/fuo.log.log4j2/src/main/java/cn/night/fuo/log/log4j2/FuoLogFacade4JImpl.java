package cn.night.fuo.log.log4j2;

import cn.night.fuo.log.LogFacade;
import org.slf4j.spi.LocationAwareLogger;

import java.util.function.Supplier;

/**
 * Created by Night on 2020/2/23.
 */
//@Slf4j
public class FuoLogFacade4JImpl implements LogFacade {
    private static final String CLASS_NAME = FuoLogFacade4JImpl.class.getName();

    private LocationAwareLogger log;

    public FuoLogFacade4JImpl(LocationAwareLogger logger) {
        this.log = logger;
    }

    private void writeLog(int level, String message, Object[] argArray, Throwable t) {
        log.log(null, CLASS_NAME, level, message, argArray, t);
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (log.isTraceEnabled()) {
            this.writeLog(LocationAwareLogger.TRACE_INT, format, arguments, null);
        }
    }

    @Override
    public void trace(Supplier<String> command) {
        if (log.isTraceEnabled()) {
            this.writeLog(LocationAwareLogger.TRACE_INT, command.get(), null, null);
        }
    }

    @Override
    public void trace(Throwable throwable, Supplier<String> command) {
        if (log.isTraceEnabled()) {
            this.writeLog(LocationAwareLogger.TRACE_INT, command.get(), null, throwable);
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (log.isDebugEnabled()) {
            this.writeLog(LocationAwareLogger.DEBUG_INT, format, arguments, null);
        }
    }

    @Override
    public void debug(Supplier<String> command) {
        if (log.isDebugEnabled()) {
            this.writeLog(LocationAwareLogger.DEBUG_INT, command.get(), null, null);
        }
    }

    @Override
    public void debug(Throwable throwable, Supplier<String> command) {
        if (log.isDebugEnabled()) {
            this.writeLog(LocationAwareLogger.DEBUG_INT, command.get(), null, throwable);
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (log.isInfoEnabled()) {
            this.writeLog(LocationAwareLogger.INFO_INT, format, arguments, null);
        }
    }

    @Override
    public void info(Supplier<String> command) {
        if (log.isInfoEnabled()) {
            this.writeLog(LocationAwareLogger.INFO_INT, command.get(), null, null);
        }
    }

    @Override
    public void info(Throwable throwable, Supplier<String> command) {
        if (log.isInfoEnabled()) {
            this.writeLog(LocationAwareLogger.INFO_INT, command.get(), null, throwable);
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (log.isWarnEnabled()) {
            this.writeLog(LocationAwareLogger.WARN_INT, format, arguments, null);
        }
    }

    @Override
    public void warn(Supplier<String> command) {
        if (log.isWarnEnabled()) {
            this.writeLog(LocationAwareLogger.WARN_INT, command.get(), null, null);
        }
    }

    @Override
    public void warn(Throwable throwable, Supplier<String> command) {
        if (log.isWarnEnabled()) {
            this.writeLog(LocationAwareLogger.WARN_INT, command.get(), null, throwable);
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (log.isErrorEnabled()) {
            this.writeLog(LocationAwareLogger.ERROR_INT, format, arguments, null);
        }
    }

    @Override
    public void error(Throwable throwable, String format, Object... arguments) {
        if (log.isErrorEnabled()) {
            this.writeLog(LocationAwareLogger.ERROR_INT, format, arguments, throwable);
        }
    }

    @Override
    public void error(Supplier<String> command) {
        if (log.isErrorEnabled()) {
            this.writeLog(LocationAwareLogger.ERROR_INT, command.get(), null, null);
        }
    }

    @Override
    public void error(Throwable throwable, Supplier<String> command) {
        if (log.isErrorEnabled()) {
            this.writeLog(LocationAwareLogger.ERROR_INT, command.get(), null, throwable);
        }
    }

    @Override
    public void error(Throwable throwable, String msg) {
        if (log.isErrorEnabled()) {
            log.error(msg, throwable);
        }
    }
}
