package com.itcast.web.controller;

import com.itcast.client.UserClient;
import com.itcast.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "queryreturn")  //给所有方法降级(通用的方法)
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    /*
    方式一
    @Autowired
    private DiscoveryClient discoveryClient;
    */
    /**
     方式二
    @Autowired
    private RibbonLoadBalancerClient client;

     */
/*

    @Autowired
    private UserClient userClient;
*/



    @GetMapping("item/{id}")
    //@HystrixCommand(fallbackMethod = "queryFindOne") //声明一个失败回滚处理函数--给该方法降级
    public String findOne(@PathVariable("id") Long id){

        /*
        方式一
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        ServiceInstance instance = instances.get(0);
        String uri = "http://"+instance.getHost()+":"+instance.getPort()+"/user/findOne/"+id;
        */
        /*
        方式二
        ServiceInstance instance = client.choose("user-service");
        String uri = "http://"+instance.getHost()+":"+instance.getPort()+"/user/findOne/"+id;
        */
        //方式三

        String url = "http://user-service/user/findOne/"+id;
        String userStr = restTemplate.getForObject(url, String.class);
        return userStr;

       // return userClient.queryById(id);
    }
    public String queryFindOne(Long id){
        return "服务器异常";

    }




    public String queryreturn(){
        return "服务器异常，太拥挤啦，请稍等哦！";
    }



}
