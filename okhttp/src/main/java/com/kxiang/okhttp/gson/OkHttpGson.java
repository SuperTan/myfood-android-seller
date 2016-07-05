package com.kxiang.okhttp.gson;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.kxiang.okhttp.OkHttpUtils;
import com.kxiang.okhttp.callback.StringCallback;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/11.
 */
public class OkHttpGson {

    private static Gson gson = new Gson();
    private static Handler handler = new Handler(Looper.getMainLooper());
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void postGsonLooperMain(String url, Map<String, String> map, final GenericityGson t) {
        OkHttpUtils
                .post()
                .url(url)
                .addAllParams(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        t.onFail();
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("response", "" + response);
                                Object o = gson.fromJson(response, t.mType);
                                t.onSucceed(o, response);
                            }
                        });

                    }
                });
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        t.onFail();
//                    }
//
//                    @Override
//                    public void onResponse(final String response) {
//
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Log.e("response", "" + response);
//                                Object o = gson.fromJson(response, t.mType);
//                                t.onSucceed(o, response);
//                            }
//                        });
//
//                    }
//                });
    }

    public static void getGsonLooperMain(String url, Map<String, String> map, final GenericityGson t) {
        OkHttpUtils
                .get()
                .url(url)
                .addAllParams(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        t.onFail();
                    }

                    @Override
                    public void onResponse(final String response, int id) {

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("response", "" + response);

                                Object o = gson.fromJson(URLDecoder.decode(response), t.mType);

                                t.onSucceed(o, response);
                            }
                        });

                    }
                });
    }

    public static void getGson(String url, Map<String, String> map, final GenericityGson t) {
        OkHttpUtils
                .get()
                .url(url)
                .addAllParams(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        t.onFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Object o = gson.fromJson(response, t.mType);
                        t.onSucceed(o, response);
                    }
                });
    }

    public static void postGson(String url, final Map<String, String> map, final GenericityGson t) {
        OkHttpUtils
                .post()
                .url(url)
                .addAllParams(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        t.onFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Object o = gson.fromJson(response, t.mType);
                        t.onSucceed(o, response);
                    }
                });
    }

    public static void getGson(String url, final GenericityGson t) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        t.onFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Object o = gson.fromJson(response, t.mType);
                        t.onSucceed(o, response);
                    }
                });
    }

    public static void postGson(String url, final GenericityGson t) {
        OkHttpUtils
                .post()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        t.onFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Object o = gson.fromJson(response, t.mType);
                        t.onSucceed(o, response);
                    }
                });
    }


    public static void postJson(String url,String json) {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(JSON, json);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
