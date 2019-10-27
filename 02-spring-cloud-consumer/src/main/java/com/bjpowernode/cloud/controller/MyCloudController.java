package com.bjpowernode.cloud.controller;

/**
 * ClassName:MyCloudController
 * Package:com.bjpowernode.cloud.controller
 * Description:Godswork
 *
 * @date:2019/10/26 0026 上午 9:45
 * @author:kdz
 */

import com.bjpowernode.cloud.hystrix.MyHystrix;
import com.bjpowernode.cloud.model.User;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Author@Father
 * 2019/10/26 0026
 */
@RestController
public class MyCloudController {


    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/web/hello")
    public String sayHello() {

        return restTemplate.getForEntity("http://localhost:8081/cloud/hello", String.class).getBody();

    }

    @RequestMapping("/web/user")
    public String getUser() {

        ResponseEntity<User> entity = restTemplate.getForEntity("http://01-spring-cloud-provider/cloud/user", User.class);

        User user = entity.getBody();
        HttpStatus statusCode = entity.getStatusCode();

        int statusCodeValue = entity.getStatusCodeValue();

        HttpHeaders headers = entity.getHeaders();

        System.out.println(user.getName() + user.getAge());

        System.out.println(statusCode);

        System.out.println(headers);

        System.out.println(statusCodeValue);

        return restTemplate.getForEntity("http://01-spring-cloud-provider/cloud/user", String.class).getBody();


    }


    @RequestMapping("/web/user2")
    @HystrixCommand(fallbackMethod = "error", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
            value = "1500")})
    public String user() {

        MultiValueMap<String, Object> multimap = new LinkedMultiValueMap<String, Object>();

        multimap.add("name", "李四");
        multimap.add("age", "18");

        restTemplate.put("http://01-spring-cloud-provider/cloud/user", multimap);


        return "success";

    }

    public String error(Throwable throwable) {

        String message = throwable.getMessage();
        System.out.println(message);
        return "服务暂时访问不通";
    }


    @RequestMapping("/web/hystrix")
    public String hystrix() {

        MyHystrix myHystrix = new MyHystrix(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);

        //同步得到结果，会阻塞当前进程
       // String execute = myHystrix.execute();


        //代码逻辑。。。。。。。。。。


        //执行完代码逻辑后，异步拿到结果，不会阻塞进程
        Future<String> queue = myHystrix.queue();

        String future = null;
        try {
            //异步拿到的结果
            future = queue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return future;
    }
}
