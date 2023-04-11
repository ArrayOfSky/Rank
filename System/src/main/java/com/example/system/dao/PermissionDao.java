package com.example.system.dao;



import com.example.commom.domain.system.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 权限数据访问接口
 * @author GYF
 */
@Mapper
public interface PermissionDao extends BaseMapper<Permission> {


}