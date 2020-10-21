package com.sorlin.servicebase.exceptionhandler;/**
 * @author 李松岭
 * @date 2020-07-25 13:02
 **/

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *@program: guli-parent
 *@description: 自定义异常类
 *@author: sorlin
 *@create: 2020-07-25 13:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;

}
