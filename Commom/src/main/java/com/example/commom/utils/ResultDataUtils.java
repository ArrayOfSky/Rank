package com.example.commom.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.commom.domain.system.Permission;
import com.example.commom.entity.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultDataUtils {

    public static <T> T getData(Result result, T t){
        Object data = result.getData();
        //转换List
        String jsonObject2 = JSON.toJSONString(data);
        T object = (T) JSON.parseObject(jsonObject2,t.getClass());
        return object;
    }

    public static List<Permission> getList(Result result){
        Object data = result.getData();
        //转换List
        String jsonObject2 = JSON.toJSONString(data);
        JSONArray list =  JSON.parseArray(jsonObject2);
        List<Permission> object = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);
            Permission user = JSON.parseObject(jsonObject.toJSONString(), Permission.class);
            object.add(user);
        }
        return object;
    }


}
