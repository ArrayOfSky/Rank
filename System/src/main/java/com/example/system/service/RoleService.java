package com.example.system.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commom.domain.system.Permission;
import com.example.commom.domain.system.Role;
import com.example.commom.utils.IdWorker;
import com.example.commom.utils.PermissionConstants;
import com.example.system.dao.RoleDao;
import com.example.system.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 角色操作业务逻辑层
 * @author GYF
 */
@Service
public class RoleService{

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PermissionService permissionService;

    /**
     * 分配权限
     */
    public void assignPerms(String roleId,List<String> permIds) {
        //1.获取分配的角色对象
        Role role = roleDao.selectById(roleId);
        //2.构造角色的权限集合
        Set<Permission> perms = new HashSet<>();
        for (String permId : permIds) {
            Permission permission = permissionDao.selectById(permId);
            //需要根据父id和类型查询API权限列表
            List<Permission> apiList = permissionService.findByTypeAndPid(PermissionConstants.PERMISSION_API, permission.getId());
            perms.addAll(apiList);//自定赋予API权限
            perms.add(permission);//当前菜单或按钮的权限
        }
        System.out.println(perms.size());
        //3.设置角色和权限的关系
        role.setPermissions(perms);
//        //4.更新角色
        roleDao.insert(role);
    }

    /**
     * 添加角色
     */
    public void save(Role role) {
        //填充其他参数
        role.setId(idWorker.nextId() + "");
        roleDao.insert(role);
    }

    /**
     * 更新角色
     */
    public void update(Role role) {
        Role targer = roleDao.selectById(role.getId());
        targer.setDescription(role.getDescription());
        targer.setName(role.getName());
        roleDao.updateById(role);
    }

    /**
     * 根据ID查询角色
     */
    public Role findById(String id) {
        return roleDao.selectById(id);
    }

    public List<Role> findAll(String companyId) {
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Role::getCompanyId,companyId);
        return roleDao.selectList(lambdaQueryWrapper);
    }

    /**
     * 删除角色
     */
    public void delete(String id) {
        roleDao.deleteById(id);
    }

    public IPage<Role> findByPage(String companyId, int page, int size) {
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Role::getCompanyId,companyId);
        Page<Role> page1 = new Page<>(page,size);
        IPage<Role> page2 = roleDao.selectPage(page1,lambdaQueryWrapper);
        return page2;
    }



}
