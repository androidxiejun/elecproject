package com.example.administrator.electronicproject.FashionFragment.http;

import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionMiddleBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by sunbin on 2016/9/6.
 */
public interface HttpService {

    //时尚圈推荐下部分GridView的数据请求
    @GET("/forum/recommend-list?ga=%2Fforum%2Frecommend-list&flag=")
    Call<FashionBottonBean> fashionBottomGridDatas();

    //时尚圈推荐中部分GridView的数据请求
    @GET("/forum/tag-recommend?ga=%2Fforum%2Ftag-recommend")
    Call<FashionMiddleBean> fashionMiddleGridDatas();

    @GET("/forum/thread/new?ga=%2Fforum%2Fthread%2Fnew&flag=")
    Call<FashionBottonBean> fashionNewestGridDatas();

}

