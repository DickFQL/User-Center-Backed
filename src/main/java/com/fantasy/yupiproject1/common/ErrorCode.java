package com.fantasy.yupiproject1.common;

public enum ErrorCode {

    Success(0,"ok",""),
    PARAM_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"请求数据为空",""),
    NOT_LOGIN(40100,"未登录",""),
    NOT_AUTH(40101,"无权限",""),
    SYSTEM_ERROR(50000,"系统内部异常",""),
    YYY_YYY(40002,"请求可能有问题", "");


    private final int code;
    private final String message;
    private final String description;

    ErrorCode(int code,String message,String description){
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
