package com.example.commom.domain.company;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * (Forecast)表实体类
 *
 * @author makejava
 * @since 2023-04-09 10:08:19
 */
@SuppressWarnings("serial")
@Data
@TableName("forecast")
public class Forecast implements Serializable {

    private static final long serialVersionUID = -4990810027542971546L;

    @ExcelProperty(value = "门店id", index = 0)
    private String companyId;
    @ExcelIgnore
    @TableId
    private String id;
    @ExcelProperty(value = "日期", index = 1)
    private String date;
    @ExcelProperty(value = "开始时间", index = 2)
    private String startTime;
    @ExcelProperty(value = "结束时间", index = 3)
    private String endTime;
    @ExcelProperty(value = "预测顾客流量", index = 4)
    private Double num;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }


}

