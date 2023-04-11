package com.example.commom.domain.table;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@TableName("bs_table")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Table
{
    @TableId
    private String id;
    private String userId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="HH:mm:ss")
    private Date startTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="HH:mm:ss")
    private Date endTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date workDay;

    private String companyId;


}
