package com.example.company.controller;


import com.example.commom.entity.Result;
import com.example.commom.entity.ResultCode;
import com.example.company.service.CompanyService;

import com.example.commom.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GYF
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/haha",method = RequestMethod.POST)
    public Result test(@RequestBody Company company){
        return new Result(ResultCode.SUCCESS,company);
    }


    /**
     * 保存
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Result save(@RequestBody Company company) {
        companyService.add(company);
        return new Result(ResultCode.SUCCESS,null);
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id,@RequestBody Company company){
        company.setId(id);
        companyService.update(company);
        return new Result(ResultCode.SUCCESS,null);
    }
    /**
     * 删除
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id){
        companyService.deleteById(id);
        return new Result(ResultCode.SUCCESS,null);
    }
    /**
     * 根据id查询
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        Company company = companyService.findById(id);
        Result<Company> result = new Result(ResultCode.SUCCESS,company);
        return result;
    }
    /**
     * 查询所有
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAll(){
        List<Company> company = companyService.findAll();
        Result<List<Company>> result = new Result(ResultCode.SUCCESS,company);
        return result;
    }


}
