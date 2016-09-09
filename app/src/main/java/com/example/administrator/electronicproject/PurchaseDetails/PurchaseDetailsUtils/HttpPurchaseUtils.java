package com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetailsUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/8.
 */
public class HttpPurchaseUtils {
    public static final String URL_PATH="http://api-v2.mall.hichao.com";
    public static PurchaseDetailsService mService;
    public static PurchaseDetailsService create(){
        if(mService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mService=retrofit.create(PurchaseDetailsService.class);
        }
        return mService;
    }
}
