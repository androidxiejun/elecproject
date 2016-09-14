package com.example.administrator.electronicproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.ExpertPersonBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.MineFragment.adapter.VIPAdapter;
import com.example.administrator.electronicproject.MineFragment.bean.VIPBean;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunbin on 2016/9/13.
 */
public class VIPPersonFeagment extends Fragment implements View.OnClickListener{

    private Context context;
    private ListView listView;
    private static int id;
    private VIPBean.DataBean.UserBean headDatas;//头部数据
    private List<VIPBean.DataBean.SkusBean> listDatas = new ArrayList<>();//列表数据
    private VIPAdapter vipAdapter;
    //头部
    private View headView;
    private CircleImageView headImage;
    private TextView userName;
    private TextView userRank;
    private ProgressBar progress;
    private TextView userNextRank;
    private TextView rule;
    private TextView userInfo;

    private static VIPCallBack callBack;

    public interface VIPCallBack{
        void ruleFragment();
    }

    public static VIPPersonFeagment newInstance(int uid,VIPCallBack call){
        id = uid;
        callBack = call;
        return new VIPPersonFeagment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.vip_fragment_person, container, false);
        listView = (ListView) inflate.findViewById(R.id.vip_fragment_list);

        headView = LayoutInflater.from(context).inflate(R.layout.vip_person_head,null);
        headImage = (CircleImageView) headView.findViewById(R.id.vip_person_head_image);
        userName = (TextView) headView.findViewById(R.id.vip_person_head_title);
        userRank = (TextView) headView.findViewById(R.id.vip_person_rankStr);
        progress = (ProgressBar) headView.findViewById(R.id.vip_person_progress);
        userNextRank = (TextView) headView.findViewById(R.id.vip_person_nextRankStr);
        rule = (TextView) headView.findViewById(R.id.vip_person_levle_rule);
        userInfo = (TextView) headView.findViewById(R.id.vip_person_head_rankInformation);

        listView.addHeaderView(headView);

        vipAdapter = new VIPAdapter(context,listDatas);
        listView.setAdapter(vipAdapter);

        rule.setOnClickListener(this);

        initDatas();

        return inflate;
    }

    private void initDatas() {

        /**
         * ListVeiw的数据
         */
        HttpUtils.create().vipDatas().enqueue(new Callback<VIPBean>() {

            @Override
            public void onResponse(Call<VIPBean> call, Response<VIPBean> response) {
                VIPBean.DataBean dataBean = response.body().getData();
                headDatas = dataBean.getUser();
                if (!headDatas.getUserAvatar().equals("") && headDatas.getUserAvatar()!=null){
                    Picasso.with(context).load(headDatas.getUserAvatar()).into(headImage);
                }
                userName.setText(headDatas.getUsername());
                userRank.setText(headDatas.getRankStr());
                userNextRank.setText(headDatas.getNextRankStr());
                progress.setMax(headDatas.getNeedPaidNextRank());
                String rate = headDatas.getRankRate().substring(0, headDatas.getRankRate().length() - 1);
                progress.setProgress(headDatas.getNeedPaidNextRank()*Integer.valueOf(rate)/100);
                userInfo.setText(headDatas.getRankInformation());

                listDatas.addAll(dataBean.getSkus());
                vipAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<VIPBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vip_person_levle_rule://跳转规则页面
                callBack.ruleFragment();
                break;
        }
    }
}
