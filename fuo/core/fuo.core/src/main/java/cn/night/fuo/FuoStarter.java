package cn.night.fuo;

import cn.night.fuo.core.env.FuoEnvironmentContext;
import cn.night.fuo.exception.check.FuoEnvironmentInitializeFailedException;
import cn.night.fuo.log.LogFacade;
import cn.night.fuo.pattern.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Created by Night on 2020/2/17.
 */
public class FuoStarter {

    @Autowired
    ApplicationContext context;

    @Autowired
    FuoConf conf;

    @Autowired
    LogFacade log;

    @Bean(value = "fuo.FuoEnvironment")
    public FuoEnvironment getBean() throws FuoEnvironmentInitializeFailedException {
        try {
            Assert.notNull(this.conf, " FuoConf Ioc failed");
            Assert.notNull(this.log, " FuoLog Ioc failed");
            FuoEnvironmentContext context = new FuoEnvironmentContext(this.conf, this.log);
            FuoEnvironment env = new FuoEnvironment();
            return env.build(context);
        } catch (Exception e) {
            throw new FuoEnvironmentInitializeFailedException(e.getMessage(), e);
        }
    }
}
