package com.example.gateway.client;

import com.example.gateway.fegin.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "oaa",configuration = FeginConfig.class)
public interface OaaClient {

    @GetMapping("/oaa/isLogin")
    String isLogin(@RequestHeader("Authorization")String Authorization);

}
