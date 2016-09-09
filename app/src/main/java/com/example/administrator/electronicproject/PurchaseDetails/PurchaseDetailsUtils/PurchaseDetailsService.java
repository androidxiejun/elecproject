package com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetailsUtils;

import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetailsBean.PurchaseDetailsBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface PurchaseDetailsService {
    @GET("/goods?version=7.0.0& source= mxyc_adr& ga= http://api-v2.mall.hichao.com& method=/goods& main_image=0& sign=0& data= eyJtYWluX2ltYWdlIjoiMCIsInNvdXJjZV9pZCI6IjEwMTMzMzUiLCJnYSI6Imh0dHA6Ly9hcGktdjIubWFsbC5oaWNoYW8uY29tIn0=& source_id=0")
    Call<PurchaseDetailsBean>queryBean();
}
