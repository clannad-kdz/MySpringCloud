package com.bjpowernode.cloud.controller;/**
 * ClassName:MyCloudController
 * Package:com.bjpowernode.cloud.controller
 * Description:Godswork
 *
 * @date:2019/10/26 0026 上午 9:45
 * @author:kdz
 */

import com.bjpowernode.cloud.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * Author@Father
 * 2019/10/26 0026
 */
@RestController
public class MyCloudController {


    @RequestMapping("/cloud/hello")
    public String hello() {

        return "hello spring-cloud-provider";
    }

    @GetMapping(value = "/cloud/user")
    public User getUser() {

        User user = new User();
        user.setName("张三 8082");
        user.setAge(10);

        return user;
    }

    @PutMapping("/cloud/user")
    public User getUser(@RequestParam("name") String name,
                        @RequestParam("age") int age) {


        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setName("8082----" + name);
        user.setAge(age);

        System.out.println(user.getName() + "---" + user.getAge());


        return user;
    }
}
