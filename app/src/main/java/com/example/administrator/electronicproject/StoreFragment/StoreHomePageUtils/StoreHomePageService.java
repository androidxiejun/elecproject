package com.example.administrator.electronicproject.StoreFragment.StoreHomePageUtils;

import com.example.administrator.electronicproject.StoreFragment.StoreHomePageBean.StoreHomePageBean;
import com.example.administrator.electronicproject.StoreFragment.StoreHomePageBean.StoreHomePageNextBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/10.
 */
public interface StoreHomePageService {
    @GET("/mall/region/new?ga=%2Fmall%2Fregion%2Fnew")
    Call<StoreHomePageBean>queryBean(@Query("region_id") int region_id);
    @GET("/sku/list?ga=%2Fsku%2Flist&flag=&more_items=1&type=selection")
    Call<StoreHomePageNextBean>queryNextBean(@Query("category_ids")String category_ids);
}
