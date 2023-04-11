package com.example.company.service;


import com.example.company.dao.CompanyDao;

import com.example.commom.domain.company.Company;
import com.example.commom.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GYF
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IdWorker idWorker;

    public void add(Company company){
        String  id = idWorker.nextId()+"";
        company.setId(id);
        company.setAuditState("0");//0 未审核 1 已审核
        company.setState(1);//0 未激活 1 已激活
        companyDao.insert(company);
    }

    public void update(Company company){
        Company temp = companyDao.selectById(company.getId());
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        companyDao.insert(temp);
    }

    public void deleteById(String id){
        companyDao.deleteById(id);
    }

    public Company findById(String id){
        return companyDao.selectById(id);
    }

    public List<Company> findAll(){
        return companyDao.selectList(null);
    }

}
