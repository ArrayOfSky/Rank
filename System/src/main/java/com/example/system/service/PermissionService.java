package com.example.system.service;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.commom.domain.system.Permission;
import com.example.commom.domain.system.PermissionApi;
import com.example.commom.domain.system.PermissionMenu;
import com.example.commom.domain.system.PermissionPoint;
import com.example.commom.entity.ResultCode;
import com.example.commom.exception.CommonException;
import com.example.commom.utils.BeanMapUtils;
import com.example.commom.utils.PermissionConstants;
import com.example.system.dao.PermissionApiDao;
import com.example.system.dao.PermissionDao;
import com.example.system.dao.PermissionPointDao;
import com.example.system.dao.PermissionMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.commom.utils.IdWorker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @author GYF
 */
@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PermissionMenuDao permissionMenuDao;

    @Autowired
    private PermissionPointDao permissionPointDao;

    @Autowired
    private PermissionApiDao permissionApiDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 1.保存权限
     */
    public void save(Map<String,Object> map) throws Exception {
        //设置主键的值
        String id = idWorker.nextId()+"";
        //1.通过map构造permission对象
        Permission perm = BeanMapUtils.mapToBean(map,Permission.class);
        //2.根据类型构造不同的资源对象（菜单，按钮，api）
        int type = perm.getType();
        switch (type) {
            case PermissionConstants.PERMISSION_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map,PermissionMenu.class);
                menu.setId(id);
                permissionMenuDao.insert(menu);
                break;
            case PermissionConstants.PERMISSION_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map,PermissionPoint.class);
                point.setId(id);
                permissionPointDao.insert(point);
                break;
            case PermissionConstants.PERMISSION_API:
                PermissionApi api = BeanMapUtils.mapToBean(map,PermissionApi.class);
                api.setId(id);
                permissionApiDao.insert(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        //3.保存
        permissionDao.insert(perm);
    }

    /**
     * 2.更新权限
     */
    public void update(Map<String,Object> map) throws Exception {
        Permission perm = BeanMapUtils.mapToBean(map,Permission.class);
        //1.通过传递的权限id查询权限
        Permission permission = permissionDao.selectById(perm.getId());
        permission.setName(perm.getName());
        permission.setCode(perm.getCode());
        permission.setDescription(perm.getDescription());
        permission.setEnVisible(perm.getEnVisible());
        //2.根据类型构造不同的资源
        int type = perm.getType();
        switch (type) {
            case PermissionConstants.PERMISSION_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map,PermissionMenu.class);
                menu.setId(perm.getId());
                permissionMenuDao.updateById(menu);
                break;
            case PermissionConstants.PERMISSION_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map,PermissionPoint.class);
                point.setId(perm.getId());
                permissionPointDao.updateById(point);
                break;
            case PermissionConstants.PERMISSION_API:
                PermissionApi api = BeanMapUtils.mapToBean(map,PermissionApi.class);
                api.setId(perm.getId());
                permissionApiDao.updateById(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        //3.保存
        permissionDao.updateById(permission);
    }

    /**
     * 3.根据id查询
     *      //1.查询权限
     *      //2.根据权限的类型查询资源
     *      //3.构造map集合
     */
    public Map<String, Object> findById(String id) throws Exception {
        Permission perm = permissionDao.selectById(id);
        int type = perm.getType();

        Object object = null;

        if(type == PermissionConstants.PERMISSION_MENU) {
            object = permissionMenuDao.selectById(id);
        }else if (type == PermissionConstants.PERMISSION_POINT) {
            object = permissionPointDao.selectById(id);
        }else if (type == PermissionConstants.PERMISSION_API) {
            object = permissionApiDao.selectById(id);
        }else {
            throw new CommonException(ResultCode.FAIL);
        }

        Map<String, Object> map = BeanMapUtils.beanToMap(object);

        map.put("name",perm.getName());
        map.put("type",perm.getType());
        map.put("code",perm.getCode());
        map.put("description",perm.getDescription());
        map.put("pid",perm.getPid());
        map.put("enVisible",perm.getEnVisible());


        return map;
    }

    public List<Permission> findByTypeAndPid(int type,String id){
        LambdaQueryWrapper<Permission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Permission::getType,type);
        lambdaQueryWrapper.eq(Permission::getPid,id);
        return permissionDao.selectList(lambdaQueryWrapper);
    }



    /**
     * 4.查询全部
     * type      : 1：菜单2：按钮（权限点）3：API接口
     * enVisible : 0：查询所有saas平台的最高权限，1：查询企业的权限
     * pid ：父id
     */
    public List<Permission> findAll(Map<String, Object> map) {
        LambdaQueryWrapper<Permission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        String pid = (String) map.get("pid");
        String enVisible = (String) map.get("enVisible");
        String type = (String) map.get("type");
      if(pid!=null){
          lambdaQueryWrapper.eq(Permission::getPid,pid);
      }
      if(enVisible!=null){
          lambdaQueryWrapper.eq(Permission::getEnVisible,enVisible);
      }
      if(type!=null){
          lambdaQueryWrapper.eq(Permission::getType,type);
      }
      return permissionDao.selectList(lambdaQueryWrapper);
    }

    /**
     * 5.根据id删除
     *  //1.删除权限
     *  //2.删除权限对应的资源
     *
     */
    public void deleteById(String id) throws Exception {
        //1.通过传递的权限id查询权限
        Permission permission = permissionDao.selectById(id);
        permissionDao.deleteById(permission.getId());
        //2.根据类型构造不同的资源
        int type = permission.getType();
        switch (type) {
            case PermissionConstants.PERMISSION_MENU:
                permissionMenuDao.deleteById(id);
                break;
            case PermissionConstants.PERMISSION_POINT:
                permissionPointDao.deleteById(id);
                break;
            case PermissionConstants.PERMISSION_API:
                permissionApiDao.deleteById(id);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
    }
}
