package com.carlinx.cloud.controller;

import com.carlinx.cloud.client.ProducerServiceInterface;
import com.carlinx.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author yj
 * @Create 2019/11/16 19:39
 */

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private ProducerServiceInterface producerServiceInterface;


    @GetMapping("/sayHello1")
    public String sayHello1(String name) {
        RestTemplate restTemplate = new RestTemplate();
        //参数1:调用url   参数2: 响应数据类型
        String result = restTemplate.getForObject("http://localhost:7012/producer/hello?name=" + name, String.class);
        return result;
    }


    @GetMapping("/sayHello2")
    public String sayHello2(String name) {
        RestTemplate restTemplate1 = new RestTemplate();
        //客户端负载均衡    serviceId不区分大小写
        ServiceInstance serviceInstance = loadBalancerClient.choose("producer");
        //获取服务端ip
        String host = serviceInstance.getHost();
        //获取服务端port
        int port = serviceInstance.getPort();
        //参数1:调用url   参数2: 响应数据类型
        String result = restTemplate1.getForObject("http://" + host + ":" + port + "/producer/hello?name=" + name, String.class);
        return result;
    }


    @GetMapping("/sayHello3")
    public String sayHello3(String name) {
        String result = restTemplate.getForObject("http://producer/producer/hello?name=" + name, String.class);
        return result;
    }


    @GetMapping("/sayHello4")
    public String sayHello4(String name) {
        String result = producerServiceInterface.sayHello(name);
        return result;
    }


    @GetMapping("/create/user")
    public void createUser() {
        User user = new User(3L, "qyy", 23, new Date());
        producerServiceInterface.saveUser(user);
    }


}
