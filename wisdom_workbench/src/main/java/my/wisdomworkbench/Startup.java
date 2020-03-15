package my.wisdomworkbench;

import cn.night.fuo.core.conf.IConf;
import cn.night.fuo.spring.SpringContextHolder;
import cn.night.fuo.web.WebConf;
import cn.night.fuo.web.cors.CORSConf;
import cn.night.fuo.web.cors.CORSInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Night on 2020/2/15.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.night.*","my.wisdomworkbench.*"})
//@ServletComponentScan
public class Startup {

    public static void main(String[] args) throws Exception {
//        System.out.println(s);

        SpringApplication application = new SpringApplication(Startup.class);
        ApplicationContext ctx = application.run(args);
      // ApplicationContext ctx = SpringApplication.run(Startup.class, args);

//
//
        Object obj = SpringContextHolder.getBean(Class.forName("cn.night.fuo.web.cors.CORSInterceptor"));

//        SpringApplication.run(Startup.class,args);
    }
}
