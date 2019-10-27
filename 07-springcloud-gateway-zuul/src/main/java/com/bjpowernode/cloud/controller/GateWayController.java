package com.bjpowernode.cloud.controller;/**
 * ClassName:ZuulController
 * Package:com.bjpowernode.cloud.controller
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 8:39
 * @author:kdz
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * zuul调用自己的接口
 * Author@Father
 * 2019/10/26 0026
 */
@RestController
public class GateWayController {

    @RequestMapping("/api/local")
    public String hello() {

        return "exec the api gateway.";
    }
}
