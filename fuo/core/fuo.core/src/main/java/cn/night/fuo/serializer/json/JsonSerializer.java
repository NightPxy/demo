package cn.night.fuo.serializer.json;

import cn.night.fuo.FuoEnvironment;
import cn.night.fuo.utils.json.JsonUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Night on 2020/2/19.
 */
@Component
public class JsonSerializer implements JsonUtils {
    @Autowired
    private FuoEnvironment env;

    @Override
    public String toJSONString(Object object) {
        return JSON.toJSONString(object, env.getSerializerEnv().getJsonSerializeConfig(),
                env.getSerializerEnv().getJsonSerializeFeatures());
    }

    @Override
    public String toJSONOriginateString(Object object) {
        return JSON.toJSONString(object);
    }
}
