package com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetailsUtils;

import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetailsBean.PurchaseDetailsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface PurchaseDetailsService {
    @GET("/goods")
    Call<PurchaseDetailsBean> queryBean(@Query("source_id")String source_id);
}
