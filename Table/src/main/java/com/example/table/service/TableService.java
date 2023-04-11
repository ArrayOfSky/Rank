package com.example.table.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commom.domain.system.User;
import com.example.commom.domain.table.Table;
import com.example.table.dao.TableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableDao tableDao;

    public List<Table> getTableListByCompanyIdAndDate(String companyId, Date date){
        LambdaQueryWrapper<Table> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Table::getCompanyId,companyId);
        lambdaQueryWrapper.eq(Table::getWorkDay,date);
        return tableDao.selectList(lambdaQueryWrapper);
    }

    public List<Table> getTableListByCompanyIdAndWeek(String companyId,Date startDate,Date endDate){
        LambdaQueryWrapper<Table> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Table::getCompanyId,companyId);
        lambdaQueryWrapper.between(Table::getWorkDay,startDate,endDate);
        return tableDao.selectList(lambdaQueryWrapper);
    }

    public void updateTable(Table table1,Table table){
        LambdaQueryWrapper<Table> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Table::getCompanyId,table1.getCompanyId());
        lambdaQueryWrapper.eq(Table::getUserId,table1.getUserId());
        lambdaQueryWrapper.eq(Table::getStartTime,table1.getStartTime());
        lambdaQueryWrapper.eq(Table::getWorkDay,table1.getWorkDay());
        lambdaQueryWrapper.eq(Table::getEndTime,table1.getEndTime());
        Table table2 = tableDao.selectOne(lambdaQueryWrapper);
        table.setId(table2.getId());
        tableDao.updateById(table);
    }



}
