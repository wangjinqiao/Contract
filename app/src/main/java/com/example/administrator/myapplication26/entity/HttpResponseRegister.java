package com.example.administrator.myapplication26.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 网络响应 注册
 */

public class HttpResponseRegister {
    //用户名
    protected String username;
    //环信id
    @SerializedName("name")
    protected String hxID;
    //表 主键
    @SerializedName("uuid")
    protected String tableKey;
    //密码
    protected String password;

    public String getUsername() {
        return username;
    }

    public String getHxID() {
        return hxID;
    }

    public String getTableKey() {
        return tableKey;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "HttpResponseRegister{" +
                "username='" + username + '\'' +
                ", hxID='" + hxID + '\'' +
                ", tableKey='" + tableKey + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
