package com.example.commom.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestHeadUtil {

    public static Map<String,String> getRequestHeads(HttpServletRequest request){
        Enumeration er = request.getHeaderNames();//获取请求头的所有name值
       Map<String,String> map = new HashMap<>();
        while(er.hasMoreElements()){
            String name	=(String) er.nextElement();
            String value = request.getHeader(name);
            map.put(name,value);
        }
        return map;
    }

}
