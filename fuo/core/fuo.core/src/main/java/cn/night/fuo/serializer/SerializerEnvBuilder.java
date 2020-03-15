package cn.night.fuo.serializer;

import cn.night.fuo.core.env.FuoEnvironmentBuilder;
import cn.night.fuo.core.env.FuoEnvironmentContext;
import cn.night.fuo.pattern.Assert;
import cn.night.fuo.serializer.json.JsonConf;
import cn.night.fuo.serializer.json.serializer.FormatConf;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Night on 2020/2/20.
 */
public class SerializerEnvBuilder extends FuoEnvironmentBuilder<SerializerEnvironment> {

    private SerializerEnvironment env = new SerializerEnvironment();

    private SerializerEnvBuilder buildJsonSerializeEnvironment(FuoEnvironmentContext context) {
        JsonConf jsonConf = context.getConf().getSerializer().getJson();

        SerializeConfig serializeConfig = new SerializeConfig();
        for (FormatConf formatConf: jsonConf.getFormats()) {
            Class<?> targetClazz = Assert.classLoad(formatConf.getTarget());
            Object formatInstance = Assert.classCreateInstance(formatConf.getFormat());
            serializeConfig.put(targetClazz,formatInstance);
        }
        env.setJsonSerializeConfig(serializeConfig);

        List<SerializerFeature> features = new ArrayList<>();
        //强制使用:禁用fastjson
//        features.add(SerializerFeature.DisableCircularReferenceDetect);
        this.env.setJsonSerializeFeatures(features.toArray(new SerializerFeature[features.size()]));
        return this;
    }

    @Override
    public SerializerEnvironment doBuild(FuoEnvironmentContext context){
        this.buildJsonSerializeEnvironment(context);

        Assert.notNull(env.getJsonSerializeConfig(),"jsonSerializeConfig initialize failed");
        Assert.notNull(env.getJsonSerializeFeatures(),"jsonSerializeFeatures initialize failed");
        return this.env;
    }
}
