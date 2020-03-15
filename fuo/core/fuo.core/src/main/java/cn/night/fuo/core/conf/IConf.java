package cn.night.fuo.core.conf;

import cn.night.fuo.exception.runtime.FuoIllegalArgumenRuntimetException;
import cn.night.fuo.utils.Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.ResolvableType;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by Night on 2020/2/17.
 */
public interface IConf { //extends InitializingBean
    default boolean inspectionAndInitializ() throws IllegalAccessException {
        this.initializing();

        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            if (!fieldType.isPrimitive()) {
                if (IConf.class.isAssignableFrom(fieldType)) {
                    IConf fieldValue = (IConf) field.get(this);
                    if (fieldValue == null) {
                        throw new FuoIllegalArgumenRuntimetException(this.getClass() + ":" + field.getName()
                                + " inspectionAndInitializ error, fieldValue can not be null");
                    }
                    fieldValue.inspectionAndInitializ();
                } else if (Collection.class.isAssignableFrom(fieldType)) {
                    Field field2 = field;
                    ResolvableType resolvableType = ResolvableType.forField(field);
                    if (resolvableType != null) {
                        ResolvableType tType = resolvableType.getGeneric(0);
                        if (tType != null) {
                            Class<?> tt = tType.resolve();
                            if (IConf.class.isAssignableFrom(tt)) {
                                Collection<?> fieldValue = (Collection<?>) field.get(this);
                                if (fieldValue == null) {
                                    throw new FuoIllegalArgumenRuntimetException(this.getClass() + ":" + field.getName()
                                            + " inspectionAndInitializ error, fieldValue can not be null");
                                }
                                for (Object value : fieldValue) {
                                    IConf conf = (IConf) value;
                                    conf.inspectionAndInitializ();
                                }
                            }
                        }
                    }
                }
            }
        }

        this.inspecting();

        return true;
    }

    default void initializing() {
    }

    default void inspecting() {

    }


//    @Override
//    default void afterPropertiesSet() throws Exception {
////        SerializeConfig config = new SerializeConfig();
////
////        com.alibaba.fastjson.serializer.SimpleDateFormatSerializer
////        com.alibaba.fastjson.serializer.Big
////
////        config.put()
////        JSON.toJSON(new Object(),SerializerFeature.);
////
////        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
////        FastJsonConfig fastJsonConfig = new FastJsonConfig();
////        fastJsonConfig.setSerializeConfig();
////        converter.setFastJsonConfig(fastJsonConfig);
//    }
}
