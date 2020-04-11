package com.itcast.client;

import com.itcast.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/item/{id}")
    String queryById(@PathVariable("id") Long id);


}
