package cn.night.fuo.web.cors;

import cn.night.fuo.core.conf.ConfConstants;
import cn.night.fuo.core.conf.IConf;
import cn.night.fuo.pattern.Assert;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Night on 2020/2/17.
 */
@Data
public class CORSConf implements IConf {
    private Boolean enable = false;
    private List<String> origins = new ArrayList<>();
    private List<String> methods = new ArrayList<>();
    private Integer age = 36000;
    private Boolean credentials = false;

    @Override
    public void initializing(){
        if(this.origins.isEmpty()){
            this.origins.add(ConfConstants.ANY);
        }
        if(this.methods.isEmpty()){
            this.methods.add(ConfConstants.ANY);
        }
    }

    @Override
    public void inspecting(){
        Assert.notNull(this.enable,"Config: web.cors.enable can not be null");
        if(this.enable){
            Assert.collectionNotEmpty(this.origins,"Config: web.cors.enable = true,So web.cors.origins can not be empty");
            Assert.collectionNotEmpty(this.methods,"Config: web.cors.enable = true,So web.cors.methods can not be empty");
            Assert.notNull(this.age,"Config: web.cors.enable = true,So web.cors.age can not be null");
            Assert.integerLimit(this.age,0,72000,"web.cors.age must limit in [0,72000]");
            Assert.notNull(this.credentials,"Config: web.cors.enable = true,So web.cors.credentials can not be null");
        }
    }
}
