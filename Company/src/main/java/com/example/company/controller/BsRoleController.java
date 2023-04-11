package com.example.company.controller;

import com.example.commom.domain.company.BsRole;
import com.example.commom.entity.Result;
import com.example.commom.entity.ResultCode;
import com.example.company.service.BsRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * BsRole
 *
 * @author makejava
 * @since 2023-04-08 20:56:36
 */
@CrossOrigin
@RestController
@RequestMapping("/company")
public class BsRoleController {
    /**
     * 服务对象
     */
    @Resource
    private BsRoleService bsRoleService;

    /**
     * 查找企业排班规则
     */
    @RequestMapping(value = "/bsRole/get/{companyId}",method = RequestMethod.GET)
    public Result getRoleByCompanyId(@PathVariable("companyId") String companyId){
        BsRole role = bsRoleService.findByCompanyId(companyId);
        return new Result(ResultCode.SUCCESS,role);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param bsRole 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "/bsRole/get", method = RequestMethod.GET)
    public Result selectOne(BsRole bsRole) {
        BsRole result = bsRoleService.selectById(bsRole.getId());
        return new Result(ResultCode.SUCCESS,result);
    }
    
    /**
     * 新增一条数据
     *
     * @param bsRole 实体类
     * @return Response对象
     */
    @RequestMapping(value = "/bsRole/insert", method = RequestMethod.POST)
    public Result insert(@RequestBody BsRole bsRole) {
        int result = bsRoleService.insert(bsRole);
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 修改一条数据
     *
     * @param bsRole 实体类
     * @return Response对象
     */
    @RequestMapping(value = "/bsRole/update", method = RequestMethod.PUT)
    public Result update(@RequestBody BsRole bsRole) {
        BsRole result = bsRoleService.update(bsRole);
     return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 删除一条数据
     *
     * @param bsRole 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "/bsRole/delete", method = RequestMethod.DELETE)
    public Result delete(BsRole bsRole) {
        int result = bsRoleService.deleteById(bsRole.getId());
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "/bsRole/selectAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<BsRole> result = bsRoleService.selectAll();
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 分页查询
     *
     * @param start 偏移
     * @param limit 条数
     * @return Response对象
     */
    @RequestMapping(value = "/bsRole/selectPage", method = RequestMethod.GET)
    public Result selectPage(Integer start, Integer limit) {
        return new Result(ResultCode.SUCCESS,bsRoleService.selectPage(start, limit));
    }
    
}

