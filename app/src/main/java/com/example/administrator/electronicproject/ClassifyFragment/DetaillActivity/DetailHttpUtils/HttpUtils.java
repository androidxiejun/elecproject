package com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity.DetailHttpUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/7.
 */
public class HttpUtils {
    public static final String URL_PATH="http://api-v2.mall.hichao.com";
    public static HttpService mHttpService;
    public static HttpService create(){
        if(mHttpService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mHttpService=retrofit.create(HttpService.class);
        }
        return mHttpService;
    }
}
