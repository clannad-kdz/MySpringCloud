package com.bjpowernode.cloud.config;/**
 * ClassName:Myconfig
 * Package:com.bjpowernode.cloud.config
 * Description:Godswork
 *
 * @date:2019/10/26 0026 上午 10:17
 * @author:kdz
 */

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Author@Father
 * 2019/10/26 0026
 */
@Configuration
public class MyConfig {


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Bean
    public IRule iRule() {

        return new RandomRule();
    }
}