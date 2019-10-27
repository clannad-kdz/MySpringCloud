package com.bjpowernode.cloud.hystrix;/**
 * ClassName:MyHystrix
 * Package:com.bjpowernode.cloud.hystrix
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 4:16
 * @author:kdz
 */

import com.netflix.hystrix.HystrixCommand;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Author@Father
 * 2019/10/26 0026
 */
public class MyHystrix extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    public MyHystrix(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    //调用远程服务
    @Override
    protected String run() throws Exception {

        MultiValueMap<String, Object> multimap = new LinkedMultiValueMap<String, Object>();

        multimap.add("name", "李四");
        multimap.add("age", "18");

        restTemplate.put("http://01-spring-cloud-provider/cloud/user", multimap);

        return "OK";
    }

    /**
     * 当远程服务超时、异常、不可用等情况时，会触发该熔断方法
     *
     * @return String
     */
    @Override
    protected String getFallback() {

        return "Fail";

    }
}
