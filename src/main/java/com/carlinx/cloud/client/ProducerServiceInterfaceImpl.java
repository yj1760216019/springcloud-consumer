package com.carlinx.cloud.client;

import com.carlinx.cloud.entity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author yj
 * @Create 2019/11/18 21:32
 */

@Component
public class ProducerServiceInterfaceImpl implements ProducerServiceInterface {
    @Override
    public String sayHello(String name) {
        return "服务器故障，请稍后再试！";
    }

    @Override
    public List<User> users() {
        User user = new User(1L, "服务器故障，请稍后再试", 12, new Date());
        return Arrays.asList(user);
    }

    @Override
    public void saveUser(User user) {

    }
}
