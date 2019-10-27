package com.bjpowernode.config.controller;/**
 * ClassName:ConfigController
 * Package:com.bjpowernode.config.controller
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 11:29
 * @author:kdz
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author@Father
 * 2019/10/26 0026
 */
@RestController
public class ConfigController {




    //第一种获取config方式
    @Value("${url}")
    private String url;

    //第二种获取config方式
    @Autowired
    Environment environment;

    @RequestMapping("config/url")
    public String url() {

        return url;

    }


    @RequestMapping("config/url2")
    public String url2() {

        return environment.getProperty("url");
    }
}
