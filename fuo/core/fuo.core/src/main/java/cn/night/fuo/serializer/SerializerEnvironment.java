package cn.night.fuo.serializer;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * Created by Night on 2020/2/21.
 */
@Data
public class SerializerEnvironment {
    private SerializeConfig jsonSerializeConfig;
    private SerializerFeature[] jsonSerializeFeatures;
}
