package com.bjpowernode.cloud.fallback;/**
 * ClassName:MyFallBackFactory
 * Package:com.bjpowernode.cloud.fallback
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 6:21
 * @author:kdz
 */

import com.bjpowernode.cloud.model.User;
import com.bjpowernode.cloud.service.TestService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Author@Father
 * 2019/10/26 0026
 */
@Component
public class MyFallBackFactory implements FallbackFactory<TestService> {


    @Override
    public TestService create(Throwable throwable) {

        return new TestService() {
            @Override
            public String hello() {

                return throwable.getMessage();

            }

            @Override
            public User getUser() {

                User user = new User();
                user.setName(throwable.getMessage());
                user.setAge(1);
                return user;

            }
        };
    }


}
