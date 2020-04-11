package com.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/*
@EnableCircuitBreaker //开启熔断
@EnableDiscoveryClient
@SpringBootApplication
*/
@EnableFeignClients
@SpringCloudApplication
public class ConsumerApp {


    @Bean
    @LoadBalanced //Ribbon默认的负载均衡策略是简单的轮询
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }



    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class,args);
    }
}



