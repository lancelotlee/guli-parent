package com.sorlin.servicebase.exceptionhandler;/**
 * @author 李松岭
 * @date 2020-07-24 19:45
 **/

import com.sorlin.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@program: guli-parent
 *@description: 统一异常处理类
 *@author: sorlin
 *@create: 2020-07-24 19:45
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.error().message("执行了自定义异常");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e){
        e.printStackTrace();
        return Result.error().message(e.getMsg()).code(e.getCode());
    }


}
