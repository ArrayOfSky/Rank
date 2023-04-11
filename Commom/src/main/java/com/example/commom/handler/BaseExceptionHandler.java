package com.example.commom.handler;


import com.example.commom.exception.CommonException;
import com.example.commom.entity.Result;
import com.example.commom.entity.ResultCode;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GYF
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result error(HttpServletRequest request, HttpServletResponse response, Exception e){

        //对common进行处理
        if(e.getClass()== CommonException.class){
            CommonException ce = (CommonException) e;
            Result result = new Result(ce.getResultCode(),null);
            return result;
        }


        Result result = new Result(ResultCode.SERVER_ERROR,null);
        return result;
    }

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response,AuthorizationException e) {
        return new Result(ResultCode.UNAUTHORISE,null);
    }

}
