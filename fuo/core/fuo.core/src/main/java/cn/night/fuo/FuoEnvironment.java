package cn.night.fuo;

import cn.night.fuo.core.env.FuoEnvironmentContext;
import cn.night.fuo.exception.check.FuoEnvironmentInitializeFailedException;
import cn.night.fuo.serializer.SerializerEnvBuilder;
import cn.night.fuo.serializer.SerializerEnvironment;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Night on 2020/2/19.
 */
@Data
@Component(value = "fuo.FuoEnvironment")
public class FuoEnvironment {

    private SerializerEnvironment serializerEnv;


    public FuoEnvironment build(FuoEnvironmentContext context) throws FuoEnvironmentInitializeFailedException {
        this.serializerEnv = new SerializerEnvBuilder().build(context);
        return this;
    }

}
