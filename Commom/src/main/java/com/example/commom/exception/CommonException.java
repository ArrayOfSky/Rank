package com.example.commom.exception;


import com.example.commom.entity.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GYF
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonException extends Exception{

    private ResultCode resultCode;


}
