package cn.night.fuo.web.cors;

import cn.night.fuo.log.LogFacade;
import cn.night.fuo.utils.string.FuoStringUtils;
import cn.night.fuo.web.WebConf;
import cn.night.fuo.web.mvc.IFuoMvcInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Night on 2020/2/17.
 */
@Component
public class CORSInterceptor implements IFuoMvcInterceptor {

    @Autowired
    private WebConf conf;

    @Autowired
    private LogFacade LogFacade;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CORSConf cors = conf.getCors();
        if (cors.getEnable()) {
            try {
                String postOrigin = request.getHeader("Origin");
                String postOriginDomain = FuoStringUtils.getDomain(postOrigin);
                if (!StringUtils.isEmpty(postOriginDomain)) {
                    if (conf.getCors().getOrigins().contains(postOriginDomain.toLowerCase())) {
                        response.setHeader("Access-Control-Allow-Origin", postOrigin);
                        //todo fix methded format
                        response.setHeader("Access-Control-Allow-Methods", cors.getMethods().toString());
                        response.setHeader("Access-Control-Max-Age", cors.getAge().toString());
                        response.setHeader("Access-Control-Allow-Credentials", cors.getCredentials().toString());
                        response.setHeader("Access-Control-Allow-Headers",
                                "Origin, X-Requested-With, Content-Type, Accept");
                    }
                }
            } catch (Exception e) {
                LogFacade.error(e, "CORSInterceptor: preHandle 异常");
            }
        }

        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
