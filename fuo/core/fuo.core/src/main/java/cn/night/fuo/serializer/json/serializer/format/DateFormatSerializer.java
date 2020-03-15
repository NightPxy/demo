package cn.night.fuo.serializer.json.serializer.format;

import cn.night.fuo.serializer.json.IFuoJsonSerializerFormat;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Night on 2020/2/19.
 */
public class DateFormatSerializer implements IFuoJsonSerializerFormat {

    public DateFormatSerializer(){

    }

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if(object == null) {
            serializer.out.writeNull();
        } else {
            Date date = (Date)object;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String text = format.format(date);
            serializer.write(text);
        }
    }
}
