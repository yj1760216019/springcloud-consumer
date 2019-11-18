package com.carlinx.cloud.client;

import com.carlinx.cloud.entity.User;
import feign.Body;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author yj
 * @Create 2019/11/17 21:26
 */

@FeignClient(name = "producer",fallback = ProducerServiceInterfaceImpl.class)   //指定断路方法
public interface ProducerServiceInterface {

    @GetMapping("/producer/hello")    //对应服务提供方的接口路径
    public String sayHello(@RequestParam(name = "name") String name);  //返回值是服务接口的返回值   参数是服务接口的参数


    @GetMapping("/producer/users")
    public List<User> users();   //服务提供返回值与此处返回值一致会自动封装成List

    @PostMapping("/producer/user")
    public void saveUser(@RequestBody User user);

}
