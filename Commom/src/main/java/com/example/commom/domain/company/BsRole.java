package com.example.commom.domain.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;

/**
 * (BsRole)表实体类
 *
 * @author makejava
 * @since 2023-04-08 20:53:58
 */
@SuppressWarnings("serial")
@Data
public class BsRole implements Serializable {

 private static final long serialVersionUID = -4990810027542971546L;

    
    private String id;
    
    private String companyId;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "HH:mm:ss")
    private Time startPrepareTime;
    
    private Double startDivisor;
    
    private String startDepartmentId;
    
    private Double flowDivisor;
    
    private Integer tableNum;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "HH:mm:ss")
    private Time closeTime;
    
    private Double closeDivisor;
    
    private Integer closeAdd;
    
    private String closeDepartmentId;
    
    private String flowDepartmentId;
    
    private String tableDepartmentId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }


    public Double getStartDivisor() {
        return startDivisor;
    }

    public void setStartDivisor(Double startDivisor) {
        this.startDivisor = startDivisor;
    }

    public String getStartDepartmentId() {
        return startDepartmentId;
    }

    public void setStartDepartmentId(String startDepartmentId) {
        this.startDepartmentId = startDepartmentId;
    }

    public Double getFlowDivisor() {
        return flowDivisor;
    }

    public void setFlowDivisor(Double flowDivisor) {
        this.flowDivisor = flowDivisor;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }


    public Double getCloseDivisor() {
        return closeDivisor;
    }

    public void setCloseDivisor(Double closeDivisor) {
        this.closeDivisor = closeDivisor;
    }

    public Integer getCloseAdd() {
        return closeAdd;
    }

    public void setCloseAdd(Integer closeAdd) {
        this.closeAdd = closeAdd;
    }

    public String getCloseDepartmentId() {
        return closeDepartmentId;
    }

    public void setCloseDepartmentId(String closeDepartmentId) {
        this.closeDepartmentId = closeDepartmentId;
    }

    public String getFlowDepartmentId() {
        return flowDepartmentId;
    }

    public void setFlowDepartmentId(String flowDepartmentId) {
        this.flowDepartmentId = flowDepartmentId;
    }

    public String getTableDepartmentId() {
        return tableDepartmentId;
    }

    public void setTableDepartmentId(String tableDepartmentId) {
        this.tableDepartmentId = tableDepartmentId;
    }

  
    }

