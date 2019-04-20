package com.net.security.utils;



public class JsonResult<T> {


    private int code;
    private String msg;
    private T data;
    public JsonResult(ResultCode resultCode, T data){
        this.code=resultCode.getCode();
        this.msg=resultCode.getMsg();
        this.data=data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
