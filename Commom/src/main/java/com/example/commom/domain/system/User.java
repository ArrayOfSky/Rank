package com.example.commom.domain.system;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 *
 * @author GYF
 */
@TableName("bs_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 4297464181093070302L;
    /**
     * ID
     */
    @TableId
    @ExcelIgnore
    private String id;
    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码", index = 0)
    private String mobile;
    /**
     * 用户名称
     */
    @ExcelProperty(value = "用户名", index = 1)
    private String username;
    /**
     * 密码
     */
    @ExcelProperty(value = "密码", index = 2)
    private String password;

    /**
     * 启用状态 0为禁用 1为启用
     */
    @ExcelProperty(value = "启用状态", index = 3)
    private Integer enableState;
    /**
     * 创建时间
     */
    @ExcelIgnore
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    @ExcelIgnore
    private String companyId;
    @ExcelProperty(value = "企业名称", index = 4)
    private String companyName;

    /**
     * 部门ID
     */
    @ExcelIgnore
    private String departmentId;

    /**
     * 入职时间
     */
    @ExcelIgnore
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date timeOfEntry;

    /**
     * 聘用形式
     */
    @ExcelProperty(value = "聘用形式", index = 5)
    private Integer formOfEmployment;

    /**
     * 工号
     */
    @ExcelProperty(value = "工号", index = 6)
    private String workNumber;

    /**
     * 管理形式
     */
    @ExcelProperty(value = "管理形式", index = 7)
    private String formOfManagement;

    /**
     * 工作城市
     */
    @ExcelProperty(value = "工作城市", index = 8)
    private String workingCity;

    /**
     * 转正时间
     */
    @ExcelIgnore
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date correctionTime;

    /**
     * 在职状态 1.在职  2.离职
     */
    @ExcelProperty(value = "在职状态", index = 9)
    private Integer inServiceStatus;

    @ExcelProperty(value = "部门名称", index = 10)
    private String departmentName;

    /**
     * level
     * String
     * saasAdmin：saas管理员具备所有权限
     * coAdmin：企业管理（创建租户企业的时候添加）
     * user：普通用户（需要分配角色）
     */
    @ExcelProperty(value = "等级", index = 11)
    private String level;

    @ExcelProperty(value = "工作日", index = 12)
    private String likeDay;
    @ExcelProperty(value = "工作时长", index = 13)
    private int likeLong;
    //    @ExcelProperty(value = "上午喜好开始时间",index = 14)
    @ExcelIgnore
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "HH:mm:ss")
    private Time likeAmStart;
    //    @ExcelProperty(value = "上午喜好结束时间",index = 15)
    @ExcelIgnore
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "HH:mm:ss")
    private Time likeAmEnd;
    //    @ExcelProperty(value = "下午喜好开始时间",index = 16)
    @ExcelIgnore
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "HH:mm:ss")
    private Time likePmStart;
    //    @ExcelProperty(value = "下午喜好结束时间",index = 17)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "HH:mm:ss")
    @ExcelIgnore
    private Time likePmEnd;

    public User(Object[] values) {
        //用户名	手机号	工号	聘用 形式	入职 时间	部门编码
        this.username = values[1].toString();
        this.mobile = values[2].toString();
        this.workNumber = new DecimalFormat("#").format(values[3]).toString();
        this.formOfEmployment = ((Double) values[4]).intValue();
        this.timeOfEntry = (Date) values[5];
        this.departmentId = values[6].toString(); //部门编码 != 部门id
    }

    @ExcelIgnore
    @TableField(exist = false)
    private Set<Role> roles = new HashSet<Role>();//用户与角色   多对多
}
