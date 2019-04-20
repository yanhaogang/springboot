package com.net.security.utils;


public enum  ResultCode {
    SUCCESS(0,"请求成功"),
    WARN(-1,"网络异常，请稍后重试"),
    FALSE(-2,"数据出错，请重新操作"),
    NULL(-3,"数据不完整");
    private int code;
    private String msg;
    ResultCode(int code,String msg){
        this.msg=msg;
        this.code=code;
    }
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
}
