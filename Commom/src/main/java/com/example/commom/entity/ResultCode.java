package com.example.commom.entity;

/**
 * @author GYF
 */
public enum ResultCode {

    SUCCESS(10000,"操作成功！"),
    //---系统错误返回码-----
    FAIL(10001,"操作失败"),
    UNAUTHENTICATED(10002,"您还未登录"),
    UNAUTHORISE(10003,"权限不足"),
    SERVER_ERROR(99999,"抱歉，系统繁忙，请稍后重试！"),
    MOBILEORPASSWORDERROR(20001,"用户名或密码错误");
    //---用户操作返回码----
    //---企业操作返回码----
    //---权限操作返回码----
    //---其他操作返回码----

    //操作是否成功
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(int code, String message){

        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
