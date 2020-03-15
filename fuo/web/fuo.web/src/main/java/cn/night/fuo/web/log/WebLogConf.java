package cn.night.fuo.web.log;

import cn.night.fuo.core.conf.ConfConstants;
import cn.night.fuo.core.conf.IConf;
import cn.night.fuo.pattern.Assert;
import cn.night.fuo.web.log.constants.WebLogIncludeInfoName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Night on 2020/2/18.
 */
@Data
public class WebLogConf implements IConf {
    private Boolean enabled = true;
    private List<String> methodFilters = new ArrayList<>();
    private List<String> includeInfos = new ArrayList<>();


    @Override
    public void initializing(){
        if (methodFilters.isEmpty()) {
            methodFilters.add(ConfConstants.ANY);
        }
        if (includeInfos.isEmpty()) {
            includeInfos.addAll(Arrays.asList(WebLogIncludeInfoName.REQUEST_URI,
                    WebLogIncludeInfoName.REQUEST_QUERYSTRING, WebLogIncludeInfoName.REQUEST_BODY,WebLogIncludeInfoName.REQUEST_METHOD,
                    WebLogIncludeInfoName.RESPONSE_BODY, WebLogIncludeInfoName.EXCEPTION));
        }
    }

    @Override
    public void inspecting(){
        Assert.notNull(this.enabled, "web.log.enabled can not be null");
        Assert.notNull(this.methodFilters, "web.log.methodFilters can not be null");
        Assert.notNull(this.includeInfos, "web.log.includeInfos can not be null");
        if(this.enabled) {
            Assert.collectionNotEmpty(this.methodFilters, "web.log.enabled = true, So web.log.methodFilters can not be empty");
            Assert.collectionNotEmpty(this.includeInfos, "web.log.enabled = true, So web.log.includeInfos can not be empty");
        }
    }

}
