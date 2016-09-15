package com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/13.
 */
public class SnapHttpUtils {
    public static SnapService mService;
    public static final String URL_PATH="http://api-v2.mall.hichao.com";
    public static SnapService create(){
        if(mService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mService= retrofit.create(SnapService.class);
        }
        return mService;
    }
}
