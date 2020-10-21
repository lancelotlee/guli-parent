package com.sorlin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: guli-parent
 * @description: 用户远程调用Vo
 * @author: sorlin
 * @create: 2020-08-27 14:03
 */
@Data
public class UcenterMemberWebInfo implements Serializable {
    private static final long serialVersionUID=1L;

    private String id;

    private String openid;

    private String mobile;

    private String password;

    private String nickname;

    private Integer sex;

    private Integer age;

    private String avatar;

    private String sign;

    private Boolean isDisabled;

    private Boolean isDeleted;

    private Date gmtCreate;

    private Date gmtModified;
}
