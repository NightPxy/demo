//package cn.night.fuo.rpc;
//
//
//import java.util.function.Function;
//
///**
// * Created by Night on 2020/2/24.
// */
//public abstract class RpcProcessor<R> {
//
//    protected abstract R executeRpc();
//
////    protected R process(){
////        return this.executeRpc();
//////        try {
//////            log.info(() -> "trace:" + traceId + " 发送请求:{}" + request.toString());
//////            R result = rpcCall.apply(request);
//////            log.info(() -> "trace:" + traceId + " 接受响应:{}" + result.toString());
//////            return result;
//////        } catch (Exception e) {
//////            log.info(e, () -> "trace:" + traceId + " 发生异常");
//////        }
////    }
//}
