package com.example.system.controller;

import com.example.commom.domain.system.Message;
import com.example.commom.entity.Result;
import com.example.commom.entity.ResultCode;
import com.example.system.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Message)控制层
 *
 * @author makejava
 * @since 2023-04-08 21:27:51
 */
@RestController
@RequestMapping("/system")
@CrossOrigin
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 审批数据调用
     *
     * @param
     * @return Response对象
     */
    @RequestMapping(value = "/message/update", method = RequestMethod.PUT)
    public Result update(@RequestBody Message message) {
        messageService.update(message);
        return new Result(ResultCode.SUCCESS,null);
    }


    /**
     * 获取未审批列表
     */
    @RequestMapping(value = "/message/get/notApprove/{companyId}",method = RequestMethod.GET)
    public Result getNotApprove(@PathVariable("companyId")String companyId){
        List<Message> list = messageService.selectListNotApproved(companyId);
        return new Result(ResultCode.SUCCESS,list);
    }

    /**
     * 获取该用户信息列表
     */
    @RequestMapping(value = "/message/get/userList/{userId}",method = RequestMethod.GET)
    public Result getListByUserId(@PathVariable("userId")String userId){
        List<Message> list = messageService.selectListByUserId(userId);
        return new Result(ResultCode.SUCCESS,list);
    }


    /**
     * 根据userId获取message
     * @return
     */
    @RequestMapping(value = "/message/get/{userId}",method = RequestMethod.GET)
    public Result selectByUserId(@PathVariable("userId") String userId){
        List<Message> list = messageService.selectByUserId(userId);
        return new Result(ResultCode.SUCCESS,list);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param message 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "/message/get", method = RequestMethod.GET)
    public Result selectOne(Message message) {
        Message result = messageService.selectById(message.getId());
        return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 新增一条数据
     *
     * @param message 实体类
     * @return Response对象
     */
    @RequestMapping(value = "/message/insert", method = RequestMethod.POST)
    public Result insert(@RequestBody Message message) {
        int result = messageService.insert(message);
    return new Result(ResultCode.SUCCESS,result);
    }


    /**
     * 删除一条数据
     *
     * @param message 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "/message/delete", method = RequestMethod.DELETE)
    public Result delete(Message message) {
        int result = messageService.deleteById(message.getId());
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "/message/selectAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<Message> result = messageService.selectAll();
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 分页查询
     *
     * @param start 偏移
     * @param limit 条数
     * @return Response对象
     */
    @RequestMapping(value = "/message/selectPage", method = RequestMethod.GET)
    public Result selectPage(Integer start, Integer limit) {
        return new Result(ResultCode.SUCCESS,messageService.selectPage(start, limit));
    }
    
}

