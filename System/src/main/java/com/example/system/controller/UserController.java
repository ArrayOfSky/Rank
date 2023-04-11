package com.example.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.commom.domain.system.User;
import com.example.commom.domain.system.response.UserResult;
import com.example.commom.entity.Result;
import com.example.commom.entity.ResultCode;
import com.example.commom.utils.EasyExcelUtil;
import com.example.system.service.UserService;
import com.example.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author GYF
 */ //1.解决跨域
@CrossOrigin
//2.声明restContoller
@RestController
//3.设置父路径
@RequestMapping(value = "/system")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;


    @GetMapping("/user/test")
    public String test() {
        return "ok";
    }

    /**
     * 用户偏好修改
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateLike",method = RequestMethod.PUT)
    public Result updateUserLike(@RequestBody User user){
        userService.updateUserLike(user);
        return new Result(ResultCode.SUCCESS,null);
    }



    /**
     * 文件上传
     */
    @PostMapping("/user/import/{companyId}/{departmentId}")
    public Result importUsers(MultipartFile file,@PathVariable("companyId")String companyId,@PathVariable("departmentId")String departmentId) throws IOException {
        userService.importUsers(file,companyId,departmentId);
        return new Result(ResultCode.SUCCESS,null);
    }


    /**
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    @GetMapping("/user/download/{fileName}/{companyId}")
    public Result download(HttpServletResponse response,@PathVariable("fileName")String fileName,@PathVariable("companyId")String companyId){
        try {
            List<User> list = userService.getAll(companyId);
            EasyExcelUtil.writerExcel(response,fileName,list,User.class);
            return new Result(ResultCode.SUCCESS,null);
        } catch (Exception e) {
            return new Result(ResultCode.FAIL,null);
        }
    }


    /**
     * 分配角色
     */
    @RequestMapping(value = "/user/assignRoles", method = RequestMethod.PUT)
    public Result save(@RequestBody Map<String, Object> map) {
        //1.获取被分配的用户id
        String userId = (String) map.get("id");
        //2.获取到角色的id列表
        List<String> roleIds = (List<String>) map.get("roleIds");
        //3.调用service完成角色分配
        userService.assignRoles(userId, roleIds);
        return new Result(ResultCode.SUCCESS, null);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/user/{companyId}/{companyName}", method = RequestMethod.POST)
    public Result save(@RequestBody User user,@PathVariable String companyId,@PathVariable String companyName) {
        //1.设置保存的企业id
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        //2.调用service完成保存企业
        userService.save(user);
        //3.构造返回结果
        return new Result(ResultCode.SUCCESS, null);
    }

    /**
     * 查询企业的部门列表
     * 指定企业id
     */
    @RequestMapping(value = "/user/{page}/{size}/{companyId}/{departmentId}", method = RequestMethod.GET)
    public Result findAll(@PathVariable int page, @PathVariable int size,@PathVariable String companyId,@PathVariable String departmentId) {
        IPage<User> pageUser = userService.findAll(page,size,companyId,departmentId);
        //3.构造返回结果
        return new Result(ResultCode.SUCCESS, pageUser);

    }
    /**
     * 根据电话查询
     */
    @RequestMapping(value = "/user/{mobile}", method = RequestMethod.POST)
    public Result findByMobile(@PathVariable(value = "mobile") String mobile) {
        User user = userService.findByMobile(mobile);
        return new Result(ResultCode.SUCCESS, user);
    }

    /**
     * 根据ID查询user
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) {
        // 添加 roleIds (用户已经具有的角色id数组)
        User user = userService.findById(id);
        UserResult userResult = new UserResult(user);
        return new Result(ResultCode.SUCCESS, userResult);
    }

    /**
     * 修改User
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id, @RequestBody User user) {
        //1.设置修改的部门id
        user.setId(id);
        //2.调用service更新
        userService.update(user);
        return new Result(ResultCode.SUCCESS, null);
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, name = "API-USER-DELETE")
    public Result delete(@PathVariable(value = "id") String id) {
        userService.deleteById(id);
        return new Result(ResultCode.SUCCESS, null);
    }

}
