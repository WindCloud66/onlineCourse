package com.houyongju.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HouYongJu
 * @create 2021-08-07 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WindException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//异常码
}
