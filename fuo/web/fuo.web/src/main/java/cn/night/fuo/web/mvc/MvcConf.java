package cn.night.fuo.web.mvc;

import cn.night.fuo.core.conf.IConf;
import cn.night.fuo.pattern.Assert;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Night on 2020/2/17.
 */
@Data
public class MvcConf implements IConf {

    private List<InterceptorConf> interceptors = new ArrayList<>();

    @Override
    public void inspecting() {
        Assert.notNull(this.interceptors,"web.mvc.interceptors can not be null");
    }
}
