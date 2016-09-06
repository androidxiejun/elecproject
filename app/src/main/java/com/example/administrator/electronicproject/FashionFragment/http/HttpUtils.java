package com.example.administrator.electronicproject.FashionFragment.http;

import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sunbin on 2016/9/6.
 */
public class HttpUtils {


    private static final String BASE_URL = "http://api-v2.mall.hichao.com";
    private static HttpService service;

    public static HttpService create(){
        if (null == service){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(HttpService.class);
        }
        return service;
    }

}
