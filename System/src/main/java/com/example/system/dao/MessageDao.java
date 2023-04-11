package com.example.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commom.domain.system.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Message)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-08 21:27:52
 */
@Mapper
public interface MessageDao extends BaseMapper<Message> {

}

