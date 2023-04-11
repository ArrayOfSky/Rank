package com.example.Oaa.shiro;

import com.example.Oaa.client.UserClient;
import com.example.commom.domain.system.User;
import com.example.commom.domain.system.response.ProfileResult;
import com.example.commom.entity.Result;
import com.example.commom.utils.ResultDataUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.example.commom.domain.system.Permission;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

//公共的realm：获取安全数据，构造权限信息
public class RankRealm extends AuthorizingRealm {

    @Override
    public void setName(String name) {
        super.setName("rankRealm");
    }

    @Autowired
    UserClient userClient;


    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ProfileResult result = (ProfileResult)principalCollection.getPrimaryPrincipal();
        Set<String> apisPerms = (Set<String>)result.getRoles().get("apis");
        SimpleAuthorizationInfo info = new  SimpleAuthorizationInfo();
        info.setStringPermissions(apisPerms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mobile = upToken.getUsername();
        String password = new String( upToken.getPassword());
        Result result1 = userClient.findByMobile(mobile);
        User user = ResultDataUtils.getData(result1,new User());
        if(user != null && user.getPassword().equals(password)) {
            ProfileResult result = null;
            if("user".equals(user.getLevel())) {
                result = new ProfileResult(user);
            }else {
                Map map = new HashMap();
                if("manager".equals(user.getLevel())) {
                    map.put("enVisible","1");
                }
               Result object = userClient.findAll(map);
                List<Permission> list = ResultDataUtils.getList(object);
                result = new ProfileResult(user,list);
            }
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result,user.getPassword(),this.getName());
            return info;
        }
        return null;
    }


}
