package cn.night.fuo.serializer;

import cn.night.fuo.core.conf.IConf;
import cn.night.fuo.pattern.Assert;
import cn.night.fuo.serializer.json.JsonConf;
import lombok.Data;

/**
 * Created by Night on 2020/2/19.
 */
@Data
public class SerializeConf implements IConf {

    private JsonConf json = new JsonConf();

    @Override
    public void inspecting(){
        Assert.notNull(this.json,"fuo.serializer.json can not be null");
//        this.json.inspectionAndInitializ();
    }
}
