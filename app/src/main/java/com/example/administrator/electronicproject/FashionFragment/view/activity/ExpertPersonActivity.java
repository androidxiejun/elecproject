package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.ExpertPersonBean;
import com.example.administrator.electronicproject.FashionFragment.bean.ExpertPersonDetailsBean;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.ExpertPersonAdapter;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunbin on 2016/9/10.
 * 个人信息界面
 */
public class ExpertPersonActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.expert_person_tool_back_btn)
    Button backBtn;
    @BindView(R.id.expert_person_tool_content_btn)
    TextView nameTv;
    @BindView(R.id.expert_person_head_attention)
    TextView attenTv;
    @BindView(R.id.expert_person_pull_list)
    PullToRefreshListView pullList;

    private Intent intent;
    private Context context;
    private int userId;
    private boolean isAttne = false;//用于判断是否关注
    //个人信息，发帖的数据源
    private List<ExpertPersonDetailsBean.ResponseBean.DataBean.ItemsBean> pullItems = new ArrayList<>();
    private ExpertPersonAdapter expertPersonAdapter;
    //用于添加头部
    private ListView refreshableView;
    private View headView;
    private CircleImageView headImage;
    private TextView workTv;
    private TextView attrNumTv;
    private TextView fansNumTv;
    private TabLayout chooseTab;
    private RelativeLayout showBigImage;
    private RelativeLayout showAttrPerson;
    private RelativeLayout showFansNum;
    private static String userAvatar;

    //TA喜欢的数据
    private List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> secondItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_person_layout);
        ButterKnife.bind(this);

        context = this;
        intent = getIntent();
        userId = intent.getIntExtra("userId", 12951489);

        initView();
        initDatas();//个人信息
    }



    private void initView() {
        backBtn.setOnClickListener(this);
        attenTv.setOnClickListener(this);

        expertPersonAdapter = new ExpertPersonAdapter(this, pullItems,secondItems);
        pullList.setAdapter(expertPersonAdapter);


        //添加头部
        refreshableView = pullList.getRefreshableView();
        headView = LayoutInflater.from(this).inflate(R.layout.expert_person_heard, null);
        refreshableView.addHeaderView(headView);
        //点击显示大图
        showBigImage = (RelativeLayout) headView.findViewById(R.id.expert_person_show_big_image);
        //点击进入关注的人界面
        showAttrPerson = (RelativeLayout) headView.findViewById(R.id.expert_person_show_attr_num);
        //点击进入粉丝界面
        showFansNum = (RelativeLayout) headView.findViewById(R.id.expert_person_show_fans_num);
        //设置点击事件
        showBigImage.setOnClickListener(this);
        showAttrPerson.setOnClickListener(this);
        showFansNum.setOnClickListener(this);

        headImage = (CircleImageView) headView.findViewById(R.id.expert_head_table_iv);
        workTv = (TextView) headView.findViewById(R.id.expert_head_table_tv);
        attrNumTv = (TextView) headView.findViewById(R.id.epert_person_attr_num_tv);
        fansNumTv = (TextView) headView.findViewById(R.id.expert_person_fans_num_tv);
        chooseTab = (TabLayout) headView.findViewById(R.id.expert_person_tab_layout);

        chooseTab.addTab(chooseTab.newTab().setText("发帖"));
        chooseTab.addTab(chooseTab.newTab().setText("TA喜欢"));
        chooseTab.setTabMode(TabLayout.MODE_FIXED);

        initFirst();//默认先显示发帖
        initListener();
    }


    private void initListener() {

        /**
         *选择发帖或者TA喜欢
         */
        chooseTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        secondItems.clear();
                        initFirst();
                        break;
                    case 1:
                        pullItems.clear();
                        initSecond();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.expert_person_tool_back_btn:
                finish();
                break;
            case R.id.expert_person_head_attention:
                if (!isAttne) {
                    ((TextView) view).setText("已关");
                    ((TextView) view).setTextColor(Color.GRAY);
                } else {
                    ((TextView) view).setText("+关注");
                    ((TextView) view).setTextColor(Color.RED);
                }
                isAttne = !isAttne;
                break;
            //点击显示大图
            case R.id.expert_person_show_big_image:
                Intent intent = new Intent(this, ShowImageActivity.class);
                intent.putExtra("url", userAvatar);
                startActivity(intent);
                break;
            //点击进入关注的人界面
            case R.id.expert_person_show_attr_num:
                Intent intentAtt = new Intent(this, ExpertFansActivity.class);
                intentAtt.putExtra("id", userId);
                intentAtt.putExtra("type", 0);
                startActivity(intentAtt);
                break;
            //点击进入粉丝界面
            case R.id.expert_person_show_fans_num:
                Intent intentFans = new Intent(this, ExpertFansActivity.class);
                intentFans.putExtra("id", userId);
                intentFans.putExtra("type", 1);
                startActivity(intentFans);
                break;
            //返回顶部按钮
            case R.id.expert_person_top_btn:
                refreshableView.setSelection(0);
                break;
        }
    }

    private void initFirst() {
        /**
         * 个人信息，发帖
         */
        HttpUtils.create().expertPersonDetailsDatas(userId).enqueue(new Callback<ExpertPersonDetailsBean>() {


            @Override
            public void onResponse(Call<ExpertPersonDetailsBean> call, Response<ExpertPersonDetailsBean> response) {
                pullItems.addAll(response.body().getResponse().getData().getItems());
                expertPersonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ExpertPersonDetailsBean> call, Throwable t) {

            }
        });
    }

    /**
     * 个人信息，TA喜欢
     */
    private void initSecond(){
        HttpUtils.create().expertTaLoveDatas(userId).enqueue(new Callback<FashionBottonBean>() {

            @Override
            public void onResponse(Call<FashionBottonBean> call, Response<FashionBottonBean> response) {
                secondItems.addAll(response.body().getResponse().getData().getItems());
                expertPersonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FashionBottonBean> call, Throwable t) {

            }
        });
    }

    private void initDatas() {
        /**
         * 个人信息
         */
        HttpUtils.create().expertPersonDatas(userId).enqueue(new Callback<ExpertPersonBean>() {
            @Override
            public void onResponse(Call<ExpertPersonBean> call, Response<ExpertPersonBean> response) {
                ExpertPersonBean.DataBean data = response.body().getData();
                userAvatar = data.getUserAvatar();
                if (!userAvatar.equals("")) {
                    Picasso.with(context).load(userAvatar).into(headImage);
                }
                if (!data.getUserTypeName().equals("")) {
                    workTv.setText(data.getUserTypeName());
                } else {
                    workTv.setVisibility(View.INVISIBLE);
                }
                attrNumTv.setText(data.getUserFollowNum());
                fansNumTv.setText(data.getUserFansNum());

                nameTv.setText(data.getUserName());
            }

            @Override
            public void onFailure(Call<ExpertPersonBean> call, Throwable t) {

            }
        });
    }



}
