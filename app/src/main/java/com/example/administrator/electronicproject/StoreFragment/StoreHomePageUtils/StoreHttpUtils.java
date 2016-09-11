package com.example.administrator.electronicproject.StoreFragment.StoreHomePageUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/10.
 */
public class StoreHttpUtils {
    public static final String URL_PATH="http://api-v2.mall.hichao.com";
    public static StoreHomePageService mService;
    public static StoreHomePageService create(){
        if(mService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mService=retrofit.create(StoreHomePageService.class);
        }
        return mService;
    }
}
