package com.example.administrator.electronicproject.StoreFragment.SubjectActvity.SubjectUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/12.
 */
public class SubjectHttpUtils {
    public static final String URL_PATH="http://api-v2.mall.hichao.com";
    public static SubjectService mService;
    public static SubjectService create(){
        if(mService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mService=retrofit.create(SubjectService.class);
        }
        return mService;
    }
}
