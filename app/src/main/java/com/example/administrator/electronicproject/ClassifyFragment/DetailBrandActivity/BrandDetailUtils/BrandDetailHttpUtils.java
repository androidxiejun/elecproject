package com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/8.
 */
public class BrandDetailHttpUtils {
    public static final String URL_PATH="http://api-v2.mall.hichao.com";
    public static BrandDetailService mService;
    public static BrandDetailService create(){
        if(mService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mService=retrofit.create(BrandDetailService.class);
        }
        return mService;
    }
}
