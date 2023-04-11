package com.example.table.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.commom.domain.table.Table;
import com.example.commom.entity.Result;
import com.example.commom.entity.ResultCode;
import com.example.table.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
//2.声明restContoller
@RestController
//3.设置父路径
@RequestMapping(value="/table")
public class TableController {

    @Autowired
    private TableService tableService;

    /**
     * 获取排班表 按日
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/get/{companyId}/{date}",method = RequestMethod.GET)
    public Result getTableListByCompanyIdAndDate(@PathVariable("companyId") String companyId,@PathVariable("date")Date date){
        List<Table> list = tableService.getTableListByCompanyIdAndDate(companyId,date);
        return new Result(ResultCode.SUCCESS,list);
    }

    /**
     * 获取排班表 按周
     * @return
     */
    @RequestMapping(value = "/get/{companyId}/{startDate}/{endDate}")
    public Result getTableListByCompanyIdAndWeek(@PathVariable("companyId")String companyId,@PathVariable("startDate")Date startDate,@PathVariable("endDate")Date endDate){
        List<Table> list = tableService.getTableListByCompanyIdAndWeek(companyId,startDate,endDate);
        return new Result(ResultCode.SUCCESS,list);
    }

    /**
     * 更新排班表
     * @param list
     * @return
     */
    @RequestMapping(value = "/update/",method = RequestMethod.PUT)
    public Result updateTable(@RequestBody List<Table> list){
        tableService.updateTable(list.get(0),list.get(1));
        return new Result(ResultCode.SUCCESS,null);
    }




}
