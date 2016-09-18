package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendDeatilsBean;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendTopDetailsCommentBean;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendTopDetailsFootBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.FashionFragment.view.CustomGridView;
import com.example.administrator.electronicproject.FashionFragment.view.FashionGridViewAdapter;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.FashionTopDetailsBuyAdapter;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.FashionTopDetailsCommentAdapter;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.RecommendTagAdapter;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.RecommendTopDetailsAdapter;
import com.example.administrator.electronicproject.FashionFragment.view.customview.FlowLayout;
import com.example.administrator.electronicproject.MainActivity;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagClickListener;
import com.squareup.picasso.Picasso;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunbin on 2016/9/7.
 * 内容详情界面
 */
public class FashionTopDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recommend_top_details_tool_back_btn)
    Button backBtn;
    @BindView(R.id.recommend_top_details_comment)
    TextView commentTv;
    @BindView(R.id.recommend_top_details_collect)
    TextView collectTv;
    @BindView(R.id.recommend_top_details_share)
    TextView shareTv;
    @BindView(R.id.recommend_top_details_pull_list)
    PullToRefreshListView mListView;
    @BindView(R.id.top_btn)
    Button topBackBtn;

    private Context context;
    private RecommendDeatilsBean.ResponseBean.DataBean dataBean;
    private boolean scrollFlag = true;
    private List<RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean> detailsLists = new ArrayList<>();
    private RecommendTopDetailsAdapter recommendTopDetailsAdapter;
    private Intent intent;
    private static int id;
    private ListView refreshableView;//根据PullToRefreshListView获取的listview，用于添加头部和底部
    //头部视图部分
    private View headView;
    private CircleImageView headRecyView;
    private TextView headNameTv;
    private TextView headDateTv;
    private TextView headAttentionTv;
    private RelativeLayout headDetails;
    private static int user_id = 0;

    //底部视图部分
    private View footView;
    private FlowTagLayout tableLayout;
    private RecommendTagAdapter recommendTagAdapter;
    private CustomGridView commentGrid;
    private CustomGridView moreGrid;
    private List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> footDatas = new ArrayList<>();
    private FashionGridViewAdapter footMoreAdapter;
    private List<RecommendTopDetailsCommentBean.DataBean.ItemsBean> commentDatas = new ArrayList<>();
    private FashionTopDetailsCommentAdapter commentAdapter;
    private TextView emptyTv;
    private CustomGridView buyGridView;
    //底部视图中购买的数据
    private List<RecommendDeatilsBean.ResponseBean.DataBean.EmbedItemsBean> buyDatas = new ArrayList<>();

    //固定参数
    private static String ga= "%2Fforum%2Fpost-new%2Frecommend-thread";
    private static String thread_id = "";
    private String come;
    private FashionTopDetailsBuyAdapter buyAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fashion_top_details);
        ButterKnife.bind(this);

        context = this;
        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        come = intent.getStringExtra("come");
        thread_id = intent.getStringExtra("thread_id");
        initView();
//        initDatas();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //上部回退
            case R.id.recommend_top_details_tool_back_btn:
                Intent intent = null;
                if (come.equals("fashion")) {
//                    intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                } else if (come.equals("table")) {
                    intent = new Intent(this, FashionTableDetailsActivity.class);
                    startActivity(intent);
                } else if(come.equals("this")){
//                    intent = new Intent(this, FashionTopDetailsActivity.class);
//                    intent.putExtra("id",footDatas.get(i).getComponent().getId());
//                    intent.putExtra("come","this");
//                    intent.putExtra("thread_id",footDatas.get(i).getComponent().getId()+"");
//                    startActivity(intent);
                    finish();
                }else if (come.equals("expert")){
                    finish();
                }else if (come.equals("person")){
                    finish();
                }
//                startActivity(intent);
//                finish();
                break;
            //底部评论
            case R.id.recommend_top_details_comment:
                //根据是否登陆决定跳转页面
                break;
            //底部喜欢
            case R.id.recommend_top_details_collect:
                //根据是否登陆决定跳转页面
                break;
            //底部分享
            case R.id.recommend_top_details_share:
                RecommendDeatilsBean.ResponseBean.DataBean.ShareActionBean.ShareBean shareBean = dataBean.getShareAction().getShare();
                Intent intentShare = new Intent();
                intentShare.setAction(Intent.ACTION_SEND);
                intentShare.setType("text/*");
                intentShare.putExtra(Intent.EXTRA_TEXT, Uri.parse(shareBean.getDetailUrl()));
                intentShare.putExtra(Intent.EXTRA_TITLE, shareBean.getTitle());
                startActivity(intentShare);
                break;
            //点击回到顶部按钮，回到顶部
            case R.id.top_btn:
                refreshableView.setSelection(0);
                break;
            case R.id.recommend_top_details_head:
                Intent intent1 = new Intent(this,ExpertPersonActivity.class);
                intent1.putExtra("userId",user_id);
                startActivity(intent1);
                break;
            case R.id.recommend_top_details_head_attention:
                //点击进行关注
                break;
            case R.id.recommend_top_details_foot_empty:
                //点击，进入更多评论
                Intent intentRe = new Intent(this,RecommendActivity.class);
                intentRe.putExtra("id",id);
                startActivity(intentRe);
                break;
        }
    }


    /**
     * 设置各控件的监听事件，为PullToRefreshListView添加适配器和监听事件
     */
    private void initView() {
        backBtn.setOnClickListener(this);
        collectTv.setOnClickListener(this);
        commentTv.setOnClickListener(this);
        shareTv.setOnClickListener(this);
        topBackBtn.setOnClickListener(this);

        //绑定PullToRefreshListView的适配器
        recommendTopDetailsAdapter = new RecommendTopDetailsAdapter(this, detailsLists);
        mListView.setAdapter(recommendTopDetailsAdapter);

        //获取listview，用于添加头部和底部
        refreshableView = mListView.getRefreshableView();
        //头部视图
        headView = LayoutInflater.from(this).inflate(R.layout.fashion_top_details_head, null);
        headDetails = (RelativeLayout) headView.findViewById(R.id.recommend_top_details_head);
        headRecyView = (CircleImageView) headView.findViewById(R.id.recommend_top_details_head_rv);
        headNameTv = (TextView) headView.findViewById(R.id.recommend_top_details_head_name);
        headDateTv = (TextView) headView.findViewById(R.id.recommend_top_details_head_date);
        headAttentionTv = (TextView) headView.findViewById(R.id.recommend_top_details_head_attention);

        headDetails.setOnClickListener(this);
        headAttentionTv.setOnClickListener(this);

        //添加头部
        refreshableView.addHeaderView(headView);

        //底部视图
        footView = LayoutInflater.from(this).inflate(R.layout.fashion_top_details_foot, null);
        tableLayout = (FlowTagLayout) footView.findViewById(R.id.recommend_top_details_foot_title);
        emptyTv = (TextView) footView.findViewById(R.id.recommend_top_details_foot_empty);
        emptyTv.setOnClickListener(this);
        //购买
        buyGridView = (CustomGridView) footView.findViewById(R.id.recommend_top_details_buy_grid);
        //评论
        commentGrid = (CustomGridView) footView.findViewById(R.id.recommend_top_details_foot_comment);
        moreGrid = (CustomGridView) footView.findViewById(R.id.recommend_top_details_foot_more);

        footMoreAdapter = new FashionGridViewAdapter(this, footDatas,null);
        moreGrid.setAdapter(footMoreAdapter);

        //评论
        commentAdapter = new FashionTopDetailsCommentAdapter(this, commentDatas);
        commentGrid.setAdapter(commentAdapter);
//        commentGrid.setEmptyView(emptyTv);

        buyAdapter = new FashionTopDetailsBuyAdapter(this,buyDatas);
        buyGridView.setAdapter(buyAdapter);

        //添加底部
        refreshableView.addFooterView(footView);


        initDatas();
        initListener();
    }

    /**
     * PullToRefreshListView的item点击监听事件。和PullToRefreshListView的滚动监听，用于判断是否显示回到顶部按钮
     */
    private void initListener() {
        /**
         *  PullToRefreshListView的item的点击监听事件
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //点击，如果是图片，就显示图片，不是无反应
                String componentType = detailsLists.get(i-2).getComponent().getComponentType();
                if (componentType.equals("cell")){
                    Intent intent = new Intent(context,ShowImageActivity.class);
                    intent.putExtra("url",detailsLists.get(i-2).getComponent().getPicUrl());
                    startActivity(intent);
                }
            }
        });

        /**
         * 详情上部分PullToRefreshListView的滚动监听，用于判断回到顶部按钮是否显示
         */
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
                        scrollFlag = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:// 是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
                        scrollFlag = false;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem < 3) {
                    topBackBtn.setVisibility(View.INVISIBLE);
                } else {
                    topBackBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        /**
         * 查看更多item的点击事件
         */
        moreGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context,FashionTopDetailsActivity.class);
                intent.putExtra("id",footDatas.get(i).getComponent().getId());
                intent.putExtra("come","this");
                intent.putExtra("thread_id",footDatas.get(i).getComponent().getId()+"");
                startActivity(intent);
            }
        });


    }

    /**
     * 网络请求数据
     */
    private void initDatas() {
        /**
         * 网络请求PullToRefreshListView的数据
         */
        HttpUtils.create().fashionRecommendTopDetailsData(id).enqueue(new Callback<RecommendDeatilsBean>() {

            @Override
            public void onResponse(Call<RecommendDeatilsBean> call, Response<RecommendDeatilsBean> response) {
                dataBean = response.body().getResponse().getData();
                //PullToRefreshListView的数据获取和适配器刷新
                if (detailsLists != null) {
                    detailsLists.clear();
                }
                detailsLists.addAll(dataBean.getItems());
                recommendTopDetailsAdapter.notifyDataSetChanged();

                //头部视图控件更改数据
                RecommendDeatilsBean.ResponseBean.DataBean.UserBean user = dataBean.getUser();
                Picasso.with(context).load(user.getUserAvatar()).into(headRecyView);
                headNameTv.setText(user.getUsername());
                headDateTv.setText(user.getDatatime());
                user_id = user.getUserId();
                headAttentionTv.setTextColor(context.getResources().getColor(R.color.colorAccent));
                headAttentionTv.setText("+关注");

                //底部视图的标签部分
                final List<RecommendDeatilsBean.ResponseBean.DataBean.TagsBean> tags = dataBean.getTags();
                recommendTagAdapter = new RecommendTagAdapter(context);
                tableLayout.setAdapter(recommendTagAdapter);
                recommendTagAdapter.onlyAddAll(tags);
                /**
                 * 详情中标签的点击事件
                 */
                tableLayout.setOnTagClickListener(new OnTagClickListener() {
                    @Override
                    public void onItemClick(FlowTagLayout parent, View view, int position) {
                        Intent intent = new Intent(context, FashionTableDetailsActivity.class);
                        //传递参数，listBeen的id
                        intent.putExtra("id", tags.get(position).getId());
                        intent.putExtra("come","topdetails");
                        intent.putExtra("title",tags.get(position).getCategory());
                        context.startActivity(intent);
//                        finish();
                    }
                });

                /**
                 * 详情中购买的数据，如果有数据就刷新适配器，没有不刷新
                 */
                if (dataBean.getEmbedItems() !=null){
                    buyDatas.addAll(dataBean.getEmbedItems());
                    buyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RecommendDeatilsBean> call, Throwable t) {

            }
        });

        /**
         * 网络请求详情foot中的更多的数据
         */
        HttpUtils.create().fashionRecommendTopDetailsFootData(thread_id,ga).enqueue(new Callback<FashionBottonBean>() {
            @Override
            public void onResponse(Call<FashionBottonBean> call, Response<FashionBottonBean> response) {
                if (footDatas != null) {
                    footDatas.clear();
                }
                footDatas.addAll(response.body().getResponse().getData().getItems());
                footMoreAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<FashionBottonBean> call, Throwable t) {

            }
        });

        /**
         * 网络请求详情foot中的评论的数据
         */
        HttpUtils.create().fashionRecommendTopDetailsCommentDatas(id).enqueue(new Callback<RecommendTopDetailsCommentBean>() {
            @Override
            public void onResponse(Call<RecommendTopDetailsCommentBean> call, Response<RecommendTopDetailsCommentBean> response) {
                RecommendTopDetailsCommentBean.DataBean data = response.body().getData();
                if (commentDatas != null) {
                    commentDatas.clear();
                }
                if (data.getItems() != null) {
                    commentDatas.addAll(data.getItems());
                    commentAdapter.notifyDataSetChanged();
                    emptyTv.setText("更多评论"+"("+data.getComment_count()+")");
                }
                if (data.getComment_count() == 0 || data.getItems() == null){
                    emptyTv.setText("暂无评论");
                    emptyTv.setClickable(false);
                }
            }

            @Override
            public void onFailure(Call<RecommendTopDetailsCommentBean> call, Throwable t) {

            }
        });
    }


}
