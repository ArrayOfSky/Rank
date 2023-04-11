package com.example.company.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commom.domain.company.BsRole;
import com.example.commom.utils.IdWorker;
import com.example.company.dao.BsRoleDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BsRole表)服务实现类
 *
 * @author makejava
 * @since 2023-04-08 20:56:38
 */
@Service("bsRoleService")
public class BsRoleService {
    @Resource
    private BsRoleDao bsRoleDao;

    @Resource
    private IdWorker idWorker;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BsRole selectById(String id) {
        return this.bsRoleDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public IPage<BsRole> selectPage(int start, int limit) {
        return this.bsRoleDao.selectPage(new Page(start,limit),null);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     public List<BsRole> selectAll() {
        return this.bsRoleDao.selectList(null);
     }
     
    
    /**
     * 新增数据
     *
     * @param bsRole 实例对象
     * @return 实例对象
     */
    public int insert(BsRole bsRole) {
        bsRole.setId(idWorker.nextId()+"");
        return this.bsRoleDao.insert(bsRole);
    }


    /**
     * 修改数据
     *
     * @param bsRole 实例对象
     * @return 实例对象
     */
    public BsRole update(BsRole bsRole) {
        this.bsRoleDao.updateById(bsRole);
        return this.selectById(bsRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(String id) {
        return this.bsRoleDao.deleteById(id);
    }

    public BsRole findByCompanyId(String companyId){
        LambdaQueryWrapper<BsRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BsRole::getCompanyId,companyId);
        return bsRoleDao.selectOne(lambdaQueryWrapper);
    }

}

