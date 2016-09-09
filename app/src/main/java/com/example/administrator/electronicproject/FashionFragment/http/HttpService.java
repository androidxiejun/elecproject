package com.example.administrator.electronicproject.FashionFragment.http;

import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionMiddleBean;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendDeatilsBean;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendTopDetailsCommentBean;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendTopDetailsFootBean;
import com.example.administrator.electronicproject.FashionFragment.bean.TableBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sunbin on 2016/9/6.
 */
public interface HttpService {

    //时尚圈推荐下部分GridView的数据请求
    @GET("/forum/recommend-list?ga=%2Fforum%2Frecommend-list&flag=")
    Call<FashionBottonBean> fashionBottomGridDatas();

    //时尚圈推荐中部分GridView的数据请求
    @GET("/forum/tag-recommend?")
    Call<FashionMiddleBean> fashionMiddleGridDatas(@Query("ga")int ga);

    //时尚圈最新数据请求
    @GET("/forum/thread/new?ga=%2Fforum%2Fthread%2Fnew&flag=")
    Call<FashionBottonBean> fashionNewestGridDatas();

    //时尚圈推荐上部的详情
    @GET("/forum/post-new?ga=%2Fforum%2Fpost-new")
    Call<RecommendDeatilsBean> fashionRecommendTopDetailsData(@Query("id")int id);

    //时尚圈推荐上部的详情的foot的数据请求
    @GET("/forum/post-new/recommend-thread?tags=611%2C631%2C391%2C601%2C682%2C683%2C684%2C1%2C725%2C798")
    Call<FashionBottonBean> fashionRecommendTopDetailsFootData(@Query("thread_id")String thread_id,@Query("ga")String ga);


    @GET("/forum/post-comments?ga=%2Fforum%2Fpost-comments&flag=")
    Call<RecommendTopDetailsCommentBean> fashionRecommendTopDetailsCommentDatas(@Query("id")int id);


    //时尚圈中部全部标签
    @GET("/forum/tag/list?ga=%2Fforum%2Ftag%2Flist")
    Call<TableBean> fashionMiddleTableDatas();

    //时尚圈中部全部标签的详情中的精选
    @GET("/forum/tag/get-new?ga=%2Fforum%2Ftag%2Fget-new")
    Call<FashionBottonBean> fashionTableDeatilsDatas(@Query("recommend")int recommend,@Query("flag")String flag,@Query("tag_id")int tag_id);
}

