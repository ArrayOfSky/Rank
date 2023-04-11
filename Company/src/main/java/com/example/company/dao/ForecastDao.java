package com.example.company.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commom.domain.company.Forecast;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Forecast)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-09 10:08:18
 */
@Mapper
public interface ForecastDao extends BaseMapper<Forecast> {

}

