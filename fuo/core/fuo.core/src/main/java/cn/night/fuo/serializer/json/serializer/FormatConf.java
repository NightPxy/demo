package cn.night.fuo.serializer.json.serializer;

import cn.night.fuo.core.conf.IConf;
import cn.night.fuo.pattern.Assert;
import lombok.Data;

/**
 * Created by Night on 2020/2/19.
 */
@Data
public class FormatConf implements IConf {
    private String target;
    private String format;

    @Override
    public void inspecting() {
        Assert.notNull(this.target, "serializer.json.formats.target can not be null");
        Assert.notNull(this.format,"serializer.json.formats.format can not be null");
    }
}
