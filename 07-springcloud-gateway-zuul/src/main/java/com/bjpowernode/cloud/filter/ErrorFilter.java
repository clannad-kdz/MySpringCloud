package com.bjpowernode.cloud.filter;/**
 * ClassName:ErrorFilter
 * Package:com.bjpowernode.cloud.controller
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 9:30
 * @author:kdz
 */

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author@Father
 * 2019/10/26 0026
 */
//@Component
public class ErrorFilter extends ZuulFilter {
    private static final Logger logger =
            LoggerFactory.getLogger(ErrorFilter.class);

    /**
     * 此方法确定此过滤器是个什么类型的过滤器
     *
     * @return
     */
    @Override
    public String filterType() {
        return "error";
    }

    /**
     * 多个过滤器时候的执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * true表示执行此过滤器
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器执行逻辑 ，返回值无用
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {


        try {
            RequestContext context = RequestContext.getCurrentContext();
            ZuulException exception = (ZuulException) context.getThrowable();
            logger.error("进入系统异常拦截", exception);
            HttpServletResponse response = context.getResponse();
            response.setContentType("application/json; charset=utf8");
            response.setStatus(exception.nStatusCode);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.print("{code:" + exception.nStatusCode + ",message:\"" +
                        exception.getMessage() + "\"}");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        } catch (Exception var5) {
            ReflectionUtils.rethrowRuntimeException(var5);
        }
        return null;
    }
}
