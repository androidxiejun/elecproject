package com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity.DetailBean.ClassifyDetailCategoryBean;
import com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity.DetailHttpUtils.HttpUtils;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassifyDetiailActivity extends AppCompatActivity implements View.OnClickListener{
    private TabLayout mTablayout;
    private TextView mText;
    private Button backBtn;
    private String query,title,sort,flag;
    private GridViewAdapter mAdapter;
    private PullToRefreshGridView mGridView;
    private int index=1;
    private Context context;
    private  List<ClassifyDetailCategoryBean.DataBean.ItemsBean> items=new ArrayList<>();
    private ClassifyDetailCategoryBean.DataBean.ItemsBean.ComponentBean component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_store_details_layout);
        context=this;
        initIntent();
        initView();
        initTabLayout();
        getInfo(query,"all","");
    }

    private void initTabLayout() {
        mTablayout.addTab(mTablayout.newTab().setText("默认"));
        mTablayout.addTab(mTablayout.newTab().setText("上新"));
        mTablayout.addTab(mTablayout.newTab().setText("销量"));
        mTablayout.addTab(mTablayout.newTab().setText("价格"));
    }

    private void initIntent() {
        Intent intent = getIntent();
        query=intent.getStringExtra("query");
        title=intent.getStringExtra("title");
    }
    private void getInfo(String query,String sort,String flag){
        HttpUtils.create().queryBean(query,sort,flag).enqueue(new Callback<ClassifyDetailCategoryBean>() {
            @Override
            public void onResponse(Call<ClassifyDetailCategoryBean> call, Response<ClassifyDetailCategoryBean> response) {
                List<ClassifyDetailCategoryBean.DataBean.ItemsBean> item = response.body().getData().getItems();
                items.addAll(item);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ClassifyDetailCategoryBean> call, Throwable t) {

            }
        });
    }
    private void initView() {
        mText= (TextView) findViewById(R.id.classify_detail_title);
        mText.setText(title);
        backBtn= (Button) findViewById(R.id.classify_detail_back_btn);
        backBtn.setOnClickListener(this);
        mTablayout= (TabLayout) findViewById(R.id.classify_detail_tab_layout);
        mGridView= (PullToRefreshGridView) findViewById(R.id.classify_detail_puul_to_gridview);
        mAdapter=new GridViewAdapter();
        mGridView.setAdapter(mAdapter);
        mGridView.setMode(PullToRefreshBase.Mode.BOTH);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                component=items.get(i).getComponent();
                Intent intent=new Intent(context, PurchaseDetails.class);
                intent.putExtra("currentPrice",component.getPrice());
                intent.putExtra("originPrice",component.getOrigin_price());
                intent.putExtra("title",component.getDescription());
                intent.putExtra("picUrl",component.getPicUrl());
                intent.putExtra("source_id",component.getAction().getSourceId());
                startActivity(intent);
            }
        });
        mGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                String label = DateUtils.formatDateTime(
                        getApplicationContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                flag="s1";
                new GetDataTask().execute();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                refreshView.getLoadingLayoutProxy().setRefreshingLabel("载入中");
                refreshView.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                refreshView.getLoadingLayoutProxy().setReleaseLabel("松手刷新");
                flag="s"+index++;
                new GetDataTask().execute();
            }
        });

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition()){
                   case 0:
                       sort="all";
                       getInfo(query,sort,flag);
                      break;
                   case 1:
                       sort="new";
                       getInfo(query,sort,flag);
                       break;
                   case 2:
                       sort="sale";
                       getInfo(query,sort,flag);
                       break;
                   case 3:
                       sort="price";
                       getInfo(query,sort,flag);
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
        finish();
    }

    /**
     * 刷新的线程，当刷新任务结束后，让刷新图标停止
     */
    private class GetDataTask extends AsyncTask<Void, Void,Boolean>
    {

        @Override
        protected Boolean doInBackground(Void... voids) {
           getInfo(query,sort,flag);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean lean) {
            super.onPostExecute(lean);
            mGridView.onRefreshComplete();
        }
    }
    class GridViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
         return items.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View containView, ViewGroup viewGroup) {
            component=items.get(i).getComponent();
            View view=containView;
            ViewHolder viewHolder=null;
            if(view==null){
               view= LayoutInflater.from(context).inflate(R.layout.classify_category_detail_item,viewGroup,false);
                viewHolder=new ViewHolder(view);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            viewHolder.img.setImageResource(R.mipmap.ic_launcher);
            viewHolder.countryName.setText(component.getCountry());
            viewHolder.discriptionTxt.setText(component.getDescription());
            viewHolder.currentPrice.setText("￥"+component.getPrice());
            viewHolder.originPrice.setText("￥"+component.getOrigin_price());
            viewHolder.originPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            Picasso.with(context).load(component.getPicUrl()).into(viewHolder.img);
            String ciecleUrl=component.getNationalFlag();
            if(ciecleUrl!=""){
                Picasso.with(context).load(component.getNationalFlag()).into(viewHolder.circleImageView);
            }
            return view;
        }
        class ViewHolder {
            public ImageView img;
            public CircleImageView circleImageView;
            public TextView discriptionTxt;
            public TextView currentPrice;
            public TextView originPrice;
            public TextView countryName;
            public ViewHolder(View view){
                view.setTag(this);
                this.img= (ImageView) view.findViewById(R.id.classify_category_detail_img);
                this.circleImageView= (CircleImageView) view.findViewById(R.id.classify_category_detail_circlrcountry);
                this.countryName= (TextView) view.findViewById(R.id.classify_category_detail_countryname);
                this.discriptionTxt= (TextView) view.findViewById(R.id.classify_category_detail_description);
                this.currentPrice= (TextView) view.findViewById(R.id.classify_category_detail_currentprice);
                this.originPrice= (TextView) view.findViewById(R.id.classify_category_detail_originprice);
            }
        }
    }

    /**
     * 用于手指向右滑动时，退出当前界面
     */
    //手指上下滑动时的最小速度
    private static final int YSPEED_MIN = 1000;

    //手指向右滑动时的最小距离
    private static final int XDISTANCE_MIN = 100;

    //手指向上滑或下滑时的最小距离
    private static final int YDISTANCE_MIN = 100;

    //记录手指按下时的横坐标。
    private float xDown;

    //记录手指按下时的纵坐标。
    private float yDown;

    //记录手指移动时的横坐标。
    private float xMove;

    //记录手指移动时的纵坐标。
    private float yMove;

    //用于计算手指滑动的速度。
    private VelocityTracker mVelocityTracker;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        createVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getRawX();
                yDown = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = event.getRawX();
                yMove= event.getRawY();
                //滑动的距离
                int distanceX = (int) (xMove - xDown);
                int distanceY= (int) (yMove - yDown);
                //获取顺时速度
                int ySpeed = getScrollVelocity();
                //关闭Activity需满足以下条件：
                //1.x轴滑动的距离>XDISTANCE_MIN
                //2.y轴滑动的距离在YDISTANCE_MIN范围内
                //3.y轴上（即上下滑动的速度）<XSPEED_MIN，如果大于，则认为用户意图是在上下滑动而非左滑结束Activity
                if(distanceX > XDISTANCE_MIN &&(distanceY<YDISTANCE_MIN&&distanceY>-YDISTANCE_MIN)&& ySpeed < YSPEED_MIN) {
                    finish();
                }
                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 创建VelocityTracker对象，并将触摸界面的滑动事件加入到VelocityTracker当中。
     *
     * @param event
     *
     */
    private void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 回收VelocityTracker对象。
     */
    private void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    /**
     *
     * @return 滑动速度，以每秒钟移动了多少像素值为单位。
     */
    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getYVelocity();
        return Math.abs(velocity);
    }
}
