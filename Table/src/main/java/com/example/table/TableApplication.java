package com.example.table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan(value = "com.example.domain.system")
@EnableFeignClients
@EnableDiscoveryClient
public class TableApplication {

    public static void main(String[] args) {

        SpringApplication.run(TableApplication.class, args);


    }

}
