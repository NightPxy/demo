package cn.night.fuo.serializer;

import cn.night.fuo.serializer.json.JsonSerializer;
import cn.night.fuo.spring.SpringContextHolder;
import cn.night.fuo.utils.json.JsonUtils;

/**
 * Created by Night on 2020/2/19.
 */
public class SerializerFacade {
    public final JsonUtils json = SpringContextHolder.getBean(JsonSerializer.class);
}
