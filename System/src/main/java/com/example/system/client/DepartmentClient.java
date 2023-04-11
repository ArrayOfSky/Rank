package com.example.system.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("company")
public interface DepartmentClient {



}
