package com.example.commom.comtroller;


import com.example.commom.entity.Result;
import com.example.commom.entity.ResultCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GYF
 */
@RestController
@CrossOrigin
public class ErrorController {

    //公共错误跳转
    @RequestMapping(value="/autherror")
    public Result autherror(int code) {
        return code ==1?new Result(ResultCode.UNAUTHENTICATED,null):new Result(ResultCode.UNAUTHORISE,null);
    }

}
