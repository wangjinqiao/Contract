package com.example.administrator.myapplication26.entity;

/**
 * 网络响应
 */

public class HttpResponse<T> {
    //响应码
    protected int code;
    //响应信息
    protected String msg;
    //具体数据
    protected T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
