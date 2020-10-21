package com.sorlin;

/**
 * @author 李松岭
 * @date 2020-07-24 10:24
 **/
public enum ResultCode {
    //成功
    SUCCESS(20000, "success"),
    //失败
    ERROR(20001, "error"),
    //未定义异常信息
    UNDEFINE(-1, "未定义异常信息"),
    //未定义异常信息
    UNLOGIN(28004, "未登录异常");

    private final int code;
    private final String msg;
    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static String msg(int code) {
        for (ResultCode m : ResultCode.values()) {
            if (m.getCode() == code) {
                return m.getMsg();
            }
        }
        return UNDEFINE.getMsg();
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
