package com.example.company.controller;


import com.example.commom.domain.company.Department;
import com.example.commom.domain.company.response.DeptListResult;
import com.example.commom.entity.Result;
import com.example.commom.entity.ResultCode;
import com.example.company.service.CompanyService;
import com.example.company.service.DepartmentService;

import com.example.commom.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GYF
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/company")
public class DepartmentController{

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/department/test",method = RequestMethod.POST)
    public Result test(@RequestBody Department department){
        System.out.println("1");
        return new Result(ResultCode.SUCCESS,department);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/department/{companyId}",method = RequestMethod.POST)
    public Result save(@RequestBody Department department,@PathVariable("companyId") String companyId){
        department.setCompanyId(companyId);
        departmentService.save(department);
        return new Result(ResultCode.SUCCESS,null);
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "/department1/{companyId}",method = RequestMethod.GET)
    public Result findAll(@PathVariable("companyId") String companyId){
        Company company = companyService.findById(companyId);
        List<Department> list = departmentService.findAll(companyId);
        DeptListResult deptListResult = new DeptListResult(company,list);
        return new Result(ResultCode.SUCCESS,deptListResult);
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/department/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        Department department = departmentService.findById(id);
        return new Result(ResultCode.SUCCESS,department);
    }

    /**
     * 更新
     */

    @RequestMapping(value = "/department/{departmentId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Department department,@PathVariable("departmentId") String departmentId){
        department.setId(departmentId);
        departmentService.update(department);
        return new Result(ResultCode.SUCCESS,department);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/department/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("id") String id){
        departmentService.deleteById(id);
        return new Result(ResultCode.SUCCESS,null);
    }

    /**
     * 根据code查询
     */
    @RequestMapping(value="/department/search",method = RequestMethod.POST)
    public Department findByCode(@RequestParam(value="code") String code,@RequestParam(value="companyId") String companyId) {
        Department dept = departmentService.findByCode(code,companyId);
        return dept;
    }





}
