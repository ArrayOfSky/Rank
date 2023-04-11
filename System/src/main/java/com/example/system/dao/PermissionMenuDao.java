package com.example.system.dao;




import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commom.domain.system.PermissionMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 企业数据访问接口
 * @author GYF
 */
@Mapper
public interface PermissionMenuDao extends BaseMapper<PermissionMenu> {

}