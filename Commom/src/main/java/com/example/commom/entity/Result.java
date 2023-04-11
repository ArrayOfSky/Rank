package com.example.commom.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author GYF
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T> {

    private Integer code;
    private String message;
    private T data;


    public Result(ResultCode code,T data) {
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }
}
