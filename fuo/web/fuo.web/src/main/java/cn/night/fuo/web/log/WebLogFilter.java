package cn.night.fuo.web.log;

import cn.night.fuo.core.conf.ConfConstants;
import cn.night.fuo.log.LogFacade;
import cn.night.fuo.web.WebConf;
import cn.night.fuo.web.log.constants.WebLogIncludeInfoName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Night on 2020/2/22.
 */
@Component
public class WebLogFilter extends OncePerRequestFilter implements Ordered {
    private int order = Ordered.LOWEST_PRECEDENCE - 8;

    public static final String SPLIT_STRING_M = "=";

    public static final String SPLIT_STRING_DOT = ", ";

    @Autowired
    private LogFacade log;

    @Autowired
    private WebConf webConf;

    @Override
    public int getOrder() {
        return order;
    }

    private boolean isDoLog(HttpServletRequest request) {
        WebLogConf conf = webConf.getLog();
        if (conf.getMethodFilters().contains(ConfConstants.ANY)) {
            return true;
        } else {
            if (conf.getMethodFilters().contains(request.getMethod())) {
                return true;
            }
        }
        return false;
    }

    private void logRequest(HttpServletRequest request, StringBuilder textBuilder) {
        WebLogConf conf = webConf.getLog();

        HttpServletRequestLogDecorator requestLogDecorator = new HttpServletRequestLogDecorator(request);
        textBuilder.setLength(0);
        textBuilder.append("received web-request:");
        List<String> includes = conf.getIncludeInfos();
        if (includes.contains(WebLogIncludeInfoName.REQUEST_URI)) {
            textBuilder.append("uri:").append(requestLogDecorator.getUri()).append(",");
        }
        if (includes.contains(WebLogIncludeInfoName.REQUEST_METHOD)) {
            textBuilder.append("method:").append(requestLogDecorator.getMethod()).append(",");
        }
        if (includes.contains(WebLogIncludeInfoName.REQUEST_BODY)) {
            textBuilder.append("body:").append(requestLogDecorator.getBody()).append(",");
        }
        if (includes.contains(WebLogIncludeInfoName.REQUEST_HEADER)) {
            textBuilder.append("header:").append(requestLogDecorator.getHeaders()).append(",");
        }
        if (includes.contains(WebLogIncludeInfoName.REQUEST_COOKIE)) {
            textBuilder.append("cookie:").append(requestLogDecorator.getCookie()).append(",");
        }
        if (includes.contains(WebLogIncludeInfoName.RESPONSE_BODY)) {
            textBuilder.append("res:").append(requestLogDecorator.getCookie()).append(",");
        }
        log.info(() -> textBuilder.toString());
    }

    private void logResponse(HttpServletResponse response, ContentCachingResponseWrapper wrapperResponse, StringBuilder textBuilder) {
        textBuilder.setLength(0);
        textBuilder.append("send web-response:");
        WebLogConf conf = webConf.getLog();
        List<String> includes = conf.getIncludeInfos();
        if (includes.contains(WebLogIncludeInfoName.RESPONSE_BODY)) {
            HttpServletResponseDecorator responseDecorator = new HttpServletResponseDecorator(response);
            String responseBodyStr = responseDecorator.getResponseBody(wrapperResponse);
            textBuilder.append(responseBodyStr);
            log.info(() -> textBuilder.toString());
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //日志trade
        ContentCachingRequestWrapper wrapperRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper(response);

        WebLogConf conf = webConf.getLog();
        StringBuilder textBuilder = new StringBuilder(200);
        try {
            if (conf.getEnabled() && isDoLog(request)) {
                logRequest(request, textBuilder);
            }
        } catch (Exception e) {
            log.error(e, () -> "WebLogInterceptor log request error");
        }

        try {
            filterChain.doFilter(wrapperRequest, wrapperResponse);
        }

        catch (Exception e) {
            log.error(e, "WebLogInterceptor log process error:{}", e.getMessage());
        }

        try {
            if (conf.getEnabled() && isDoLog(request)) {
                logResponse(response, wrapperResponse, textBuilder);
            }
        } catch (Exception e) {
            log.error(e, () -> "WebLogInterceptor log response error");
        }


//        String urlParams = getRequestParams(request);
//
//
//        String requestBodyStr = getRequestBody(wrapperRequest);


        wrapperResponse.copyBodyToResponse();
    }

    /**
     * 打印请求参数
     *
     * @param request
     */
    private String getRequestBody(ContentCachingRequestWrapper request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    payload = "[unknown]";
                }
                return payload.replaceAll("\\n", "");
            }
        }
        return "";
    }


    /**
     * 获取请求地址上的参数
     *
     * @param request
     * @return
     */
    public static String getRequestParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> enu = request.getParameterNames();
        //获取请求参数
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            sb.append(name + SPLIT_STRING_M).append(request.getParameter(name));
            if (enu.hasMoreElements()) {
                sb.append(SPLIT_STRING_DOT);
            }
        }
        return sb.toString();
    }


}
