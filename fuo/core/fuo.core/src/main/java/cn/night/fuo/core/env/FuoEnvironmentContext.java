package cn.night.fuo.core.env;

import cn.night.fuo.FuoConf;
import cn.night.fuo.log.LogFacade;
import lombok.Data;

/**
 * Created by Night on 2020/2/20.
 */
@Data
public class FuoEnvironmentContext {
    private FuoConf conf;
    private LogFacade log;

    public FuoEnvironmentContext(FuoConf conf,LogFacade log){
        this.conf = conf;
        this.log = log;
    }
}
