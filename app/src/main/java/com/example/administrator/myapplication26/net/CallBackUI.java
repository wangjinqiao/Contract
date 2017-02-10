package com.example.administrator.myapplication26.net;

import android.os.Handler;
import android.os.Looper;

import com.example.administrator.myapplication26.entity.HttpResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.logging.LogRecord;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 封装CallBack，得到在主线程的方法
 */

public abstract class CallBackUI<T> implements Callback {
    /**
     * 通过主线程的looper，将代码放到主线程的消息队列
     */
    protected Handler handler = new Handler(Looper.getMainLooper());
    protected HttpResponse<T> httpResponse;

    @Override
    public void onFailure(final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailureUI(call, e);
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        if (!response.isSuccessful()) {
            //响应失败
            throw new IOException("error code:" + response.code());

        }
        //解析数据
        String string = response.body().string();

        httpResponse = new Gson().fromJson(string, HttpResponse.class);

        handler.post(new Runnable() {
            @Override
            public void run() {
                onResponseUI(call, httpResponse);
            }
        });
    }

    public abstract void onFailureUI(Call call, IOException e);

    public abstract void onResponseUI(Call call, HttpResponse<T> httpResponse);
}
