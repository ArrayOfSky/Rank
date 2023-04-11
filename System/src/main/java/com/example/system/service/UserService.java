package com.example.system.service;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commom.domain.system.Role;
import com.example.commom.domain.system.User;
import com.example.commom.utils.IdWorker;
import com.example.system.dao.RoleDao;
import com.example.system.dao.UserDao;
import com.example.system.listener.ImportListener;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author GYF
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleDao roleDao;

    public void updateUserLike(User user){
        User temp = userDao.selectById(user.getId());
        temp.setLikeDay(user.getLikeDay());
        temp.setLikeLong(user.getLikeLong());
        temp.setLikeAmStart(user.getLikeAmStart());
        temp.setLikeAmEnd(user.getLikeAmEnd());
        temp.setLikePmStart(user.getLikePmStart());
        temp.setLikePmEnd(user.getLikePmEnd());
        System.out.println(temp);
        userDao.updateById(user);
    }




    public void importUsers(MultipartFile file, @PathVariable("companyId") String companyId, @PathVariable("departmentId") String departmentId) throws IOException {
        EasyExcel.read(file.getInputStream(), User.class, new ImportListener(this,companyId,departmentId)).sheet().doRead();
    }

    public List<User> getAll(String companyId) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getCompanyId, companyId);
        return userDao.selectList(lambdaQueryWrapper);
    }

    /**
     * 1.保存用户
     */
    public void save(User user) {
        //设置主键的值
        String id = idWorker.nextId() + "";
        String password = new Md5Hash(user.getPassword(), user.getMobile(), 3).toString();  //1.密码，盐，加密次数
        user.setPassword(password);//设置初始密码
        user.setEnableState(1);
        user.setId(id);
        System.out.println(user);
        userDao.insert(user);
    }

    /**
     * 2.更新用户
     */
    public void update(User user) {
        //1.根据id查询部门
        User target = userDao.selectById(user.getId());
        //2.设置部门属性
        target.setUsername(user.getUsername());
        target.setPassword(user.getPassword());
        target.setDepartmentId(user.getDepartmentId());
        target.setDepartmentName(user.getDepartmentName());
        //3.更新部门
        userDao.updateById(target);
    }

    /**
     * 3.根据id查询用户
     */
    public User findById(String id) {
        return userDao.selectById(id);
    }

    /**
     * 4.查询全部用户列表
     * 参数：map集合的形式
     * hasDept
     * departmentId
     * companyId
     */
    public IPage<User> findAll(int page, int size, String companyId, String departmentId) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getCompanyId, companyId);
        lambdaQueryWrapper.eq(User::getDepartmentId, departmentId);
        Page<User> page1 = new Page<>(page, size);
        IPage<User> page2 = userDao.selectPage(page1, lambdaQueryWrapper);
        return page2;

    }

    /**
     * 5.根据id删除用户
     */
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    public void assignRoles(String userId, List<String> roleIds) {
        User user = userDao.selectById(userId);
        Set<Role> roles = new HashSet<>();
        for (String roleId : roleIds) {
            Role role = roleDao.selectById(roleId);
            roles.add(role);
        }
        user.setRoles(roles);
        userDao.insert(user);
    }

    public User findByMobile(String mobile) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getMobile, mobile);
        return userDao.selectOne(lambdaQueryWrapper);
    }

}
