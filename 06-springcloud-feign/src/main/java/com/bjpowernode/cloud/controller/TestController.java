package com.bjpowernode.cloud.controller;/**
 * ClassName:TestConctroller
 * Package:com.bjpowernode.cloud.controller
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 5:55
 * @author:kdz
 */

import com.bjpowernode.cloud.model.User;
import com.bjpowernode.cloud.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Author@Father
 * 2019/10/26 0026
 */
@RestController
public class TestController {


    @Resource
    TestService testService;


    @RequestMapping("/web/hello")
    public String hello() {

        String hello = testService.hello();

        return hello;

    }


    @RequestMapping("/web/users")
    public User users() {

        User users = testService.getUser();

        return users;

    }
}
