package my.wisdomworkbench.controller;

import cn.night.fuo.exception.runtime.FuoIllegalArgumenRuntimetException;
import cn.night.fuo.log.LogFacade;
import cn.night.fuo.rpc.Rpc;
import cn.night.fuo.rpc.jsf2.consumer.Jsf;
import cn.night.fuo.spring.SpringContextHolder;
import cn.night.fuo.utils.Utils;
import com.jd.jsf.annotation.Consumer;
import com.jd.user.sdk.export.UserInfoExportService;
import com.jd.user.sdk.export.domain.UserBaseInfoVo;
import com.jd.user.sdk.export.exception.UserSdkExportException;
import com.wangyin.goldeneye.facade.customerlevel.api.CustomerLevelUserService;
import com.wangyin.goldeneye.facade.customerlevel.request.LevelUserRequest;
import com.wangyin.goldeneye.facade.customerlevel.response.CustomerLevelResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Night on 2020/2/15.
 */
@RestController
public class UserController {

    @Autowired
    private LogFacade log;

    @Value("${env.name}")
    private String a;

    @Value("${env.id}")
    private String alias;

    @Value("${env.name}")
    private String gg;

    @Consumer(name = "com.jd.user.sdk.export.UserInfoExportService")
    private UserInfoExportService userInfoExportService;

    @Consumer(name = "com.wangyin.goldeneye.facade.customerlevel.api.CustomerLevelUserService") //alias = "GOLDEN-TEST"
    private CustomerLevelUserService customerLevelUserService;

    @RequestMapping("hello")
    public String hello() throws Exception {
        String mobile = "18980823543";
        UserBaseInfoVo userBaseInfoVo = userInfoExportService.getUserBaseInfoByMobile(mobile, -1, 1);
        System.out.println(userBaseInfoVo);

        log.info("AAA:{}","BBB");
        log.info(()->"AAA"+":"+"BBB");

//        return a;
        return "";
    }


    @RequestMapping("user")
    public String user(LevelUserRequestVm request) throws Exception {
        LevelUserRequest rpcRequest = new LevelUserRequest();
        rpcRequest.setJdpin(request);

        LevelUserResponseVM response = new LevelUserResponseVM();

        String traceId = this.getTrace(request);
        CustomerLevelResponse<?> rpcResponse = Rpc.sincal(customerLevelUserService::queryUser)
                        .name("用户信息查询").trace(traceId)
                        .apply(rpcRequest).response();

        if (this.rpcResponse != null ) {
            List<LevelUserItemVM> datas = rpcResponse.getResult().map(rpcModel-> convertTo(rpcModel))
            return response
                    .sucess(datas)
                    .total(rpcResponse.getTotalCount());
        }
        else {
            return response
                    .failed(ResponseCodeEnum.ERROR)
        }

    }

//     Rpc.parallel()
//                .call(customerLevelUserService::queryUser).name("异步用户信息查询1").apply(request).callback(res -> {
//        })
//                .then().call(customerLevelUserService::queryUser).name("异步用户信息查询2").apply(request).callback(res -> {
//        })
//                .then().call(customerLevelUserService::queryUser).name("异步用户信息查询3").apply(request).callback(res -> {
//        })
//                .awaitResponse();
//
////        Rpc.rpcCall().sincal(customerLevelUserService::queryUser);
////         Rpc.rpcCall().sincal(userInfoExportService::getUserBaseInfoByUid).process2(1,2);

    @RequestMapping("test")
    public Test test() throws Exception {


        //@RequestBody
        Test test = new Test();

        String str = Utils.serializer.json.toJSONString(test);
        System.out.println(str);


        LevelUserRequest request = new LevelUserRequest();
        LogFacade a = SpringContextHolder.getBean(LogFacade.class);
        a.info(() -> "");
        return test;
    }


    @Data
    class Test {
        private String a = "123";
        private Date date = new Date();
    }


}

//class RpcCallDemo {
//
//    /*
//    *  杰夫或Dubbo服务注入
//    */
//    @Consumer
//    private CustomerLevelUserService rpcCustomerLevelUserService;
//
//    @Consumer(name = "com.jd.user.sdk.export.UserInfoExportService")
//    private UserInfoExportService userInfoExportService;
//
//    /*
//     * 同步调用Demo
//     */
//    public void sync() {
//        LevelUserRequest rpcRequest = new LevelUserRequest();
//        rpcRequest.setJdpin("123");
//
//        // 最简代码
//        CustomerLevelResponse<?> r1 = Rpc.sincal(rpcCustomerLevelUserService::queryUser).apply(rpcRequest).response();
//        System.out.println(r1);
//
//        // name: 功能调用名称,写入日志
//        CustomerLevelResponse<?> r2 = Rpc.sincal(rpcCustomerLevelUserService::queryUser)
//                .name("用户信息查询").trace("your request trace")
//                .apply(rpcRequest).response();
//        System.out.println(r2);
//    }
//
//    public void async() throws UserSdkExportException {
//        LevelUserRequest rpcRequest = new LevelUserRequest();
//        rpcRequest.setJdpin("123");
//        HashMap<String,String> map = new HashMap<>();
//
//        String jdpin = "123";
//        String email = "111";
//        String trace = UUID.randomUUID().toString();
//        /*
//         * 多接口 单参,多参
//         * 多个接口调用并行执行,最后一个接口调用完成后解除阻塞
//         * 超时机制依赖Rpc自身的超时调用设置
//         */
//
//        Rpc.parallel()
//            .call(userInfoExportService::checkEmailIsUsed).name("邮箱用没用").trace(trace)
//               .apply(jdpin).callback(res -> { map.put("checkEmailIsUsed", res.toString()); }).then()
//            .call(userInfoExportService::checkAliasExist).name("alias用没用").trace(trace)
//               .apply(jdpin).callback(res -> { map.put("checkEmailIsUsed", res.toString()); }).then()
//            .call(userInfoExportService::checkPinPassword).name("XXX")
//               .apply(jdpin,email).callback(res -> { map.put("alias", res.getAliaName()); }).then()
//            .awaitResponse();
//
//        if(map.containsKey("alias")){
//            //...........
//        }
//    }
//}
