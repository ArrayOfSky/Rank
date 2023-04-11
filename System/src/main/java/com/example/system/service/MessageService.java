package com.example.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commom.domain.system.Message;
import com.example.commom.entity.Result;
import com.example.commom.utils.IdWorker;
import com.example.system.dao.MessageDao;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Message表)服务实现类
 *
 * @author makejava
 * @since 2023-04-08 21:27:52
 */
@Service("messageService")
public class MessageService {
    @Resource
    private MessageDao messageDao;

    @Resource
    private IdWorker idWorker;


    /**
     * 获取未审批列表
     */
    public List<Message> selectListNotApproved(String companyId){
        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Message::getCompanyId,companyId);
        lambdaQueryWrapper.eq(Message::getState,"未处理");
        return messageDao.selectList(lambdaQueryWrapper);
    }

    /**
     * 获取该用户信息列表
     */
    public List<Message> selectListByUserId(String userId){
        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Message::getUserId,userId);
        return messageDao.selectList(lambdaQueryWrapper);
    }

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    public void update(Message message) {
        Message temp = messageDao.selectById(message.getId());
        temp.setState(message.getState());
        this.messageDao.updateById(temp);
    }



    public List<Message> selectByUserId(String userId){
        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Message::getUserId,userId);
        return messageDao.selectList(lambdaQueryWrapper);
    }



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Message selectById(String id) {
        return this.messageDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public IPage<Message> selectPage(int start, int limit) {
        return this.messageDao.selectPage(new Page(start,limit),null);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     public List<Message> selectAll() {
        return this.messageDao.selectList(null);
     }
     
    
    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    public int insert(Message message) {
        message.setId(idWorker.nextId()+"");
        return this.messageDao.insert(message);
    }




    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(String id) {
        return this.messageDao.deleteById(id);
    }
    
}

