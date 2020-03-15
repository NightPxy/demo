package cn.night.fuo.web;

import cn.night.fuo.core.conf.IConf;
import cn.night.fuo.pattern.Assert;
import cn.night.fuo.web.cors.CORSConf;
import cn.night.fuo.web.log.WebLogConf;
import cn.night.fuo.web.mvc.MvcConf;
import cn.night.fuo.web.serializer.WebSerializerConf;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Night on 2020/2/17.
 */
@Component
@ConfigurationProperties(prefix = "fuo.web")
@Data
public class WebConf implements IConf, InitializingBean {

    private WebLogConf log = new WebLogConf();

    private CORSConf cors = new CORSConf();

    private MvcConf mvc = new MvcConf();

    private WebSerializerConf serializer = new WebSerializerConf();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.inspectionAndInitializ();
    }


    @Override
    public void inspecting() {
        Assert.notNull(this.serializer, "fuo.web.serializer can not be null");
        Assert.notNull(this.mvc, "fuo.web.mvc can not be null");
        Assert.notNull(this.cors, "fuo.web.cors can not be null");
        Assert.notNull(this.log, "fuo.web.log can not be null");
    }
}
