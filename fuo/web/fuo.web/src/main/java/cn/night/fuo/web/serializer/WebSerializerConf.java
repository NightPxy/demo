package cn.night.fuo.web.serializer;

import cn.night.fuo.core.conf.IConf;
import cn.night.fuo.pattern.Assert;
import lombok.Data;

/**
 * Created by Night on 2020/2/21.
 */
@Data
public class WebSerializerConf implements IConf {
    private Boolean useFuoJsonConfig = true;

    @Override
    public void inspecting(){
        Assert.notNull(this.useFuoJsonConfig,"web.useFuoJsonConfig can not be null");
    }
}
