package com.houyongju.servicebase.exceptionhandler;

import com.houyongju.commonutils.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author HouYongJu
 * @create 2021-08-06 16:44
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultMessage error(Exception e){
        e.printStackTrace();
        return ResultMessage.error().message("执行全局异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResultMessage error(ArithmeticException e){
        e.printStackTrace();
        return ResultMessage.error().message("执行ArithmeticException异常处理");
    }

    @ExceptionHandler(WindException.class)
    @ResponseBody
    public ResultMessage error(WindException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultMessage.error().code(e.getCode()).message(e.getMsg());
    }
}
