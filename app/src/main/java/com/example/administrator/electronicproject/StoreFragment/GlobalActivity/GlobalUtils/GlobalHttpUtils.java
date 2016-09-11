package com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalUtils;

import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalService.GlobalService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/11.
 */
public class GlobalHttpUtils {
    public static final String URL="http://api-v2.mall.hichao.com";
    public static GlobalService mService;
    public static GlobalService create(){
        if(mService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mService=retrofit.create(GlobalService.class);
        }
        return mService;
    }
}
