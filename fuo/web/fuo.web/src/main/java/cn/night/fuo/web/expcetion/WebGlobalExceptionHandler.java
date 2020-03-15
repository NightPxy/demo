package cn.night.fuo.web.expcetion;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Night on 2020/2/24.
 */
@ControllerAdvice
public class WebGlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception e){
        Map<String,Object> map=new HashMap<>();
        map.put("url",request.getRequestURL().toString());
        map.put("msg",e.getMessage());
        return map;
    }
}
