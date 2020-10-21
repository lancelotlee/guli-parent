package com.sorlin.ucenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: guli-parent
 * @description: 登录返回对象
 * @author: sorlin
 * @create: 2020-08-19 16:15
 */
@Data
@ApiModel(value="登录返回对象", description="登录返回对象")
public class LoginInfoVo {

    @ApiModelProperty(value = "会员id")
    private String id;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 1 女，2 男")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户签名")
    private String sign;


}
