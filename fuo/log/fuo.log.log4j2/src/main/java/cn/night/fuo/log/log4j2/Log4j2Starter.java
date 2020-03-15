package cn.night.fuo.log.log4j2;

import cn.night.fuo.log.LogFacade;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * Created by Night on 2020/2/16.
 */

public class Log4j2Starter {
    @ConditionalOnClass(LogFacade.class)
    @Bean("fuo.log.LogFacade")
    public LogFacade build() {
        LocationAwareLogger log = (LocationAwareLogger) LoggerFactory.getLogger("cn.night.fuo.log.log4j2.Log4j2Starter");
        return  new FuoLogFacade4JImpl(log);
    }
}
