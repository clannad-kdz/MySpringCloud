package com.bjpowernode.cloud.controller;/**
 * ClassName:ErrorHandlerController
 * Package:com.bjpowernode.cloud.controller
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 8:42
 * @author:kdz
 */

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 自定义的全局异常过滤器，测试无效  TODO
 *
 * Author@Father
 * 2019/10/26 0026
 */
@RestController
public class ErrorHandlerController implements ErrorController {


    /**
     * 出异常后进入该方法，交由下面的方法处理
     */
    @Override
    public String getErrorPath() {

        return "/error";
    }



    @RequestMapping("/error")
    public Object error(){
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulException exception = (ZuulException)ctx.getThrowable();
        return exception.nStatusCode + "--" + exception.getMessage();
    }
}
