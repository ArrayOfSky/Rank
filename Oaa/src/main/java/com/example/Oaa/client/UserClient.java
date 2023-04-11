package com.example.Oaa.client;


import com.example.commom.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("system")
public interface UserClient {

    @PostMapping("/system/user/{mobile}")
    Result findByMobile(@PathVariable("mobile") String mobile);

    @GetMapping("/system/permission")
    Result findAll(@RequestParam Map map);

}
