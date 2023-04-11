package com.example.company.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commom.domain.company.BsRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BsRole)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-08 20:56:38
 */
@Mapper
public interface BsRoleDao extends BaseMapper<BsRole> {

}

