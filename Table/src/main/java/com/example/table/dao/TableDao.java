package com.example.table.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commom.domain.table.Table;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableDao extends BaseMapper<Table> {

}
