package com.example.commom.domain.system;

import lombok.Data;

import java.io.Serializable;

/**
 * (Message)表实体类
 *
 * @author makejava
 * @since 2023-04-08 21:27:53
 */
@SuppressWarnings("serial")
@Data
public class Message implements Serializable {

 private static final long serialVersionUID = -4990810027542971546L;

    
    private String id;
    //已查看 未查看 已处理
    private String state;
    //请假 申请公选
    private String kind;
    
    private String userId;
    
    private String departmentId;
    
    private String companyId;
    //理由
    private String text;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

  
    }

