package com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalService;

import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.FirCustomGridBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.GlobalTopImageBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.RecyclerBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.SecCustomGridBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.TabBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/11.
 */
public interface GlobalService {
    @GET("/region/detail/brands?ga=%2Fregion%2Fdetail%2Fbrands")
    Call<RecyclerBean>queryRecyclerBean(@Query("id")int id);
    @GET("/region/detail/daily-goods?ga=%2Fregion%2Fdetail%2Fdaily-goods")
    Call<FirCustomGridBean>queryFirGridBean(@Query("id")int id);
    @GET("/region/detail/goods?query=%E9%80%9B%E5%85%A8%E7%90%83&sort=all&ga=%2Fregion%2Fdetail%2Fgoods&flag=&asc=1")
    Call<SecCustomGridBean>querySecGridBean(@Query("cat") String cat,@Query("id")int id);
    @GET("/region/detail/goods-nav?ga=%2Fregion%2Fdetail%2Fgoods-nav")
    Call<TabBean>queryTabBean(@Query("region")int region);
    @GET("/region/detail/banner?ga=%2Fregion%2Fdetail%2Fbanner")
    Call<GlobalTopImageBean>queryImageBean(@Query("id")int region);
}
