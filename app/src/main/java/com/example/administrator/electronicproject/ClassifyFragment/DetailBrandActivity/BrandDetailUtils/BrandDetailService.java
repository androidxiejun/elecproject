package com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailUtils;

import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailBean.BrandDetailBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface BrandDetailService {
    @GET("/business-shop/list?cid=&business_id=3051&version=7.0.0&source=mxyc_adr&sort=1&ga=http%3A%2F%2Fapi-v2.mall.hichao.com&method=%2Fbusiness-shop%2Flist&flag=&sign=034d86a54a75a22e178f12fab76a1da7&data=eyJidXNpbmVzc19pZCI6IjMwNTEiLCJzb3J0IjoiMSIsImZsYWciOiIiLCJ0eXBlIjoiMiIsImNpZCI6IiIsImdhIjoiaHR0cDovL2FwaS12Mi5tYWxsLmhpY2hhby5jb20ifQ%3D%3D&type=2")
    Call<BrandDetailBean>queryBean();
}
