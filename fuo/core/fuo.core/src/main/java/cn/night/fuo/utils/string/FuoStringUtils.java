package cn.night.fuo.utils.string;

import cn.night.fuo.log.LogFacade;
import cn.night.fuo.spring.SpringContextHolder;
import org.springframework.util.StringUtils;

/**
 * Created by Night on 2020/2/17.
 */
public class FuoStringUtils {

    private static LogFacade LogFacade = SpringContextHolder.getBean(LogFacade.class);

    public static String getDomain(String url) {
        try {
            if (StringUtils.isEmpty(url)) return url;
            String[] s_URL = url.split("//");
            String urlClear = "";
            if (s_URL.length >= 2) {
                urlClear = s_URL[1];
            } else if (s_URL.length == 1) {
                urlClear = s_URL[0];
            }
            String[] s_domain = urlClear.split(":");
            if (s_domain.length > 0) {
                return s_domain[0];
            }
            return url;
        } catch (Exception e) {
            LogFacade.error(e,"CORSInterceptor: getDomain读取失败" + url);
            return url;
        }
    }
}
