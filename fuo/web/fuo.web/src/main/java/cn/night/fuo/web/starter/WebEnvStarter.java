package cn.night.fuo.web.starter;

import cn.night.fuo.web.log.WebLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by Night on 2020/2/23.
 */
public class WebEnvStarter {

//    @Bean
//    public FilterRegistrationBean<WebLogFilter> registerLoginCheckFilter(WebLogFilter requireLoginFilter) {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(requireLoginFilter);
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setName("loginCheckFilter");
//        registrationBean.setOrder(registrationBean.getOrder());
//        return registrationBean;
//    }
}
