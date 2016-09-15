package com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailUtils;

import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailBean.BrandDetailBean;
import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailBean.BrandDetailTopBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface BrandDetailService {
    @GET("/business-shop/list?cid=&version=7.0.0&source=mxyc_adr&ga=http%3A%2F%2Fapi-v2.mall.hichao.com&method=%2Fbusiness-shop%2Flist&flag=&type=2")
    Call<BrandDetailBean>queryBean(@Query("business_id")String business_id,@Query("sort")int sort);
    @GET("/business-shop?version=7.0.0&source=mxyc_adr&ga=http%3A%2F%2Fapi-v2.mall.hichao.com&method=%2Fbusiness-shop&type=1")
    Call<BrandDetailTopBean>queryTopBean(@Query("business_id")String business_id);
}
