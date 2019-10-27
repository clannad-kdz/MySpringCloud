package com.bjpowernode.cloud.filter;/**
 * ClassName:AuthFilter
 * Package:com.bjpowernode.cloud.filter
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 8:31
 * @author:kdz
 */

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 不会过滤zuul服务转发给自己controller的请求
 * Author@Father
 * 2019/10/26 0026
 */
@Component
public class AuthFilter extends ZuulFilter {

    //返回值为过滤器的类型，过滤器的类型决定了过滤器在
//哪个生命周期执行，pre 表示在路由之前执行过滤器，其他值还有 post、error、
//route 和 static，当然也可以自定义。
    @Override
    public String filterType() {

        return "pre";
    }

    //如果有多个过滤器  判断执行顺序级别
    @Override
    public int filterOrder() {
        return 0;
    }


    //判断过滤器是否执行 true执行,false不执行
    @Override
    public boolean shouldFilter() {
        //逻辑

        return true;
    }


    //run 方法则表示过滤的具体逻辑，如果请求地址中携带了 token 参数的话，
    //则认为是合法请求，否则为非法请求，如果是非法请求的话，首先设置
    //ctx.setSendZuulResponse(false); 表示不对该请求进行路由，然后设置响应码
    //和响应值。这个 run 方法的返回值目前暂时没有任何意义，可以返回任意值。
    @Override
    public Object run() throws ZuulException {

        //通过此异常测试ErrorFilter触发情况
        //int a = 10 / 0;

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
        if (token == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);

            ctx.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
            ctx.setResponseBody("非法访问");
        }
        return null;

    }
}
