package com.bjpowernode.cloud.fallback;/**
 * ClassName:MyFallBack
 * Package:com.bjpowernode.cloud.fallback
 * Description:Godswork
 *
 * @date:2019/10/26 0026 下午 5:56
 * @author:kdz
 */

import com.bjpowernode.cloud.model.User;
import com.bjpowernode.cloud.service.TestService;
import org.springframework.stereotype.Component;

/**
 * Author@Father
 * 2019/10/26 0026
 */
//@Component
public class MyFallBack implements TestService {


    @Override
    public String hello() {

        return "error";

    }

    @Override
    public User getUser() {

        User user = new User();
        user.setName("error user");
        user.setAge(0);
        return user;
    }


}
