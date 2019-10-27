package com.bjpowernode.cloud.service;/**
 * ClassName:TestService
 * Package:com.bjpowernode.cloud.service
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 5:53
 * @author:kdz
 */

import com.bjpowernode.cloud.fallback.MyFallBackFactory;
import com.bjpowernode.cloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author@Father
 * 2019/10/26 0026
 */
@FeignClient(name = "01-spring-cloud-provider", fallbackFactory = MyFallBackFactory.class)
public interface TestService {

    @RequestMapping("/cloud/hello")
    String hello();


    @GetMapping(value = "/cloud/user")
    User getUser();

}
