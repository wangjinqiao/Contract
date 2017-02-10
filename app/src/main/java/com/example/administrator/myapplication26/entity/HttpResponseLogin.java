package com.example.administrator.myapplication26.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 网络响应 登录
 */

public class HttpResponseLogin extends HttpResponseRegister {
    //用户图像
    @SerializedName("other")
    protected String photoUser;
    //昵称
    protected String nickname;

    public String getPhotoUser() {
        return photoUser;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "HttpResponseLogin{" +
                "photoUser='" + photoUser + '\'' +
                ", nickname='" + nickname + '\'' +
                '}' + "{" +
                "username='" + username + '\'' +
                ", hxID='" + hxID + '\'' +
                ", tableKey='" + tableKey + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
