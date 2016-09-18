package com.example.administrator.electronicproject.FashionFragment.http;

import com.example.administrator.electronicproject.FashionFragment.bean.ExpertBean;
import com.example.administrator.electronicproject.FashionFragment.bean.ExpertPersonBean;
import com.example.administrator.electronicproject.FashionFragment.bean.ExpertPersonDetailsBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FansBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionMiddleBean;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendDeatilsBean;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendTopDetailsCommentBean;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendTopDetailsFootBean;
import com.example.administrator.electronicproject.FashionFragment.bean.TableBean;
import com.example.administrator.electronicproject.MineFragment.bean.VIPBean;

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
    @GET("/forum/recommend-list?last_item_id=0&ga=%2Fforum%2Frecommend-list&flag=0")
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

    //时尚圈推荐上部的详情的评论的数据请求
    @GET("/forum/post-comments?ga=%2Fforum%2Fpost-comments&flag=")
    Call<RecommendTopDetailsCommentBean> fashionRecommendTopDetailsCommentDatas(@Query("id")int id);


    //时尚圈中部全部标签
    @GET("/forum/tag/list?ga=%2Fforum%2Ftag%2Flist")
    Call<TableBean> fashionMiddleTableDatas();

    //时尚圈中部全部标签的详情中的精选
    @GET("/forum/tag/get-new?ga=%2Fforum%2Ftag%2Fget-new")
    Call<FashionBottonBean> fashionTableDeatilsDatas(@Query("recommend")int recommend,@Query("flag")String flag,@Query("tag_id")int tag_id);

    //衣橱达人的数据
    @GET("/forum/star?ga=%2Fforum%2Fstar")
    Call<ExpertBean> experDatas(@Query("flag")int flag);

    //衣橱达人，点击头像后进入界面中的个人信息
    @GET("/user/info")
    Call<ExpertPersonBean> expertPersonDatas(@Query("uid")int uid);

    //衣橱达人，点击头像后进入界面中的发帖
    @GET("/user/hi-zone/get-new?lite_thread=1")
    Call<ExpertPersonDetailsBean> expertPersonDetailsDatas(@Query("uid")int uid);

    //衣橱达人，点击头像后进入界面中的TA喜欢
    @GET("/collect/collections/get-thread?ga=%2Fcollect%2Fcollections%2Fget-thread&flag=")
    Call<FashionBottonBean> expertTaLoveDatas(@Query("user_id")int user_id);

    //衣橱达人，粉丝界面数据
    @GET("/forum/fans?ga=%2Fforum%2Ffans&flag=")
    Call<FansBean> expertFansDatas(@Query("flag")int flag,@Query("id")int id,@Query("type")int type);

    @GET("/member/daily-skus?gc=hichao&gf=android&gn=mxyc_adr&gv=701&gi=133524436105315&gs=720x1280&gos=7.0.1&access_token=3p-JsegysRSnIrlgqjSCmGjHpln7cPrM1LbAVROkG3A")
    Call<VIPBean> vipDatas();
}

