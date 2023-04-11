package com.example.company.service;


import com.example.commom.domain.company.Department;
import com.example.company.dao.DepartmentDao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.commom.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author GYF
 */
@Service
public class DepartmentService{

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private IdWorker idWorker;

    public void save(Department department){
        String id  = idWorker.nextId()+"";
        department.setId(id);
        departmentDao.insert(department);
    }

    public void update(Department department){
        Department dept = departmentDao.selectById(department.getId());
        if(dept!=null){
            dept.setCode(department.getCode());
            dept.setIntroduce(department.getIntroduce());
            dept.setName(department.getName());
           departmentDao.updateById(dept);
        }
    }

    public Department findById(String id){
        return departmentDao.selectById(id);
    }

    public void deleteById(String id){
        departmentDao.deleteById(id);
    }

    public List<Department> findAll(String companyId){
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getCompanyId, companyId);
        List<Department> list = departmentDao.selectList(lambdaQueryWrapper);
        return list;
    }


    public Department findByCode(String code, String companyId) {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getCode, code).eq(Department::getCompanyId,companyId);
        Department dept = departmentDao.selectOne(lambdaQueryWrapper);
        return dept;
    }



}
