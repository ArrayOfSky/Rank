package com.example.Oaa;

import com.example.Oaa.shiro.RankRealm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.example")
@EntityScan(value = "com.example.domain")
@EnableFeignClients
@EnableDiscoveryClient
public class OaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(OaaApplication.class,args);
    }

    @Bean
    public RankRealm rankRealm(){
        return new RankRealm();
    }

//    @Bean
//    public Decoder feignDecoder() {
//        return new FeignResultDecoder();
//    }

}