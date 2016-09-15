package com.example.administrator.electronicproject.StoreFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.StoreFragment.CustomGridView.CustomGridView;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalActivity;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapActivity;
import com.example.administrator.electronicproject.StoreFragment.StoreHomePageBean.StoreHomePageBean;
import com.example.administrator.electronicproject.StoreFragment.StoreHomePageBean.StoreHomePageNextBean;
import com.example.administrator.electronicproject.StoreFragment.StoreHomePageUtils.StoreHttpUtils;
import com.example.administrator.electronicproject.StoreFragment.SubjectActvity.ProjectActivity;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/10.
 */
public class StoreHomePageFragment extends Fragment {
    private Context context;
    private ViewPager mViewPager;
    private CustomGridView mGridView;
    private MyViewPagerAdaoter pagerAdapter;
    private MyGridAdapter gridAdapter;
    private StringAdapter adapter;
    private TabLayout mTabLayout;
    private int childCount;
    private int index;
    private LinearLayout linearLayout;
    private LinearLayout mIndicatorLayout;
    private UltimateRecyclerView recyclerView;
    private static StoreHomePageBean.DataBean dataBean;
    public static final String URL_PATH = "http://api-v2.mall.hichao.com";
    private List<StoreHomePageNextBean.DataBean.ItemsBean> items;
    private List<StoreHomePageBean.DataBean> beanList = new ArrayList<>();
    private List<StoreHomePageBean.DataBean.RegionPicturesBean> region_pictures;
    private List<StoreHomePageBean.DataBean.RegionSkusBean> region_skus;
    private StoreHomePageNextBean.DataBean.ItemsBean.ComponentBean component;
    public static final String PIC_URL="http://s0.mingxingyichu.cn/group5/M00/9D/2B/wKgBfVfSNrWAShWUAAMy_sVfjOU578.jpg?imageMogr2?imageMogr2?imageMogr2";
    public static final String PIC_URL2="http://s0.mingxingyichu.cn/group6/M00/B4/FB/wKgBjVfROi6AKRcVAASlcb3jFOI741.jpg?imageMogr2?imageMogr2?imageMogr2";
    private List<String>urlList=new ArrayList<>();
    public static StoreHomePageFragment newInstance() {
        return new StoreHomePageFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_home_page_fargment_layout, container, false);
        initInfo("");
        initData();
        initListInfo(1);
        initView(view);
        controlIndicator(0);
        mViewPager.setCurrentItem(0);
        mHandler.sendEmptyMessageDelayed(1,2000);
        return view;
    }

    private void initData() {
        urlList.add(PIC_URL2);
        urlList.add(PIC_URL);
    }

    private Handler mHandler =new Handler(){
       @Override
       public void handleMessage(Message msg) {
           mViewPager.setCurrentItem(index++%2);
           mHandler.sendEmptyMessageDelayed(1,4000);
       }
   };
    private void controlIndicator(int index) {
        ImageView view = (ImageView) mIndicatorLayout.getChildAt(index);
        //初始化所有的ImageView的enable属性为false
        for (int i = 0; i < childCount; i++) {
            ImageView childView = (ImageView) mIndicatorLayout.getChildAt(i);
            childView.setEnabled(false);
        }
        view.setEnabled(true);
    }

    private void initListInfo(int index) {
        while (index < 6) {
            StoreHttpUtils.create().queryBean(index++).enqueue(new Callback<StoreHomePageBean>() {
                @Override
                public void onResponse(Call<StoreHomePageBean> call, Response<StoreHomePageBean> response) {
                    StoreHomePageBean.DataBean data = response.body().getData();
                    beanList.add(data);
                    if (beanList.size() == 5) {
                        gridAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<StoreHomePageBean> call, Throwable t) {

                }
            });
        }

    }

    /**
     * 获取下部分的网络数据，并加载视图
     */
    private void initInfo(String category_ids) {
        StoreHttpUtils.create().queryNextBean(category_ids).enqueue(new Callback<StoreHomePageNextBean>() {
            @Override
            public void onResponse(Call<StoreHomePageNextBean> call, Response<StoreHomePageNextBean> response) {
                items = response.body().getData().getItems();
                adapter = new StringAdapter(items);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<StoreHomePageNextBean> call, Throwable t) {
            }
        });
    }


    private void initView(View view) {
        mIndicatorLayout= (LinearLayout) view.findViewById(R.id.store_home_linear_layout);
        childCount=mIndicatorLayout.getChildCount();
        mTabLayout = (TabLayout) view.findViewById(R.id.store_home_grid_tab_layout);
        recyclerView = (UltimateRecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        pagerAdapter = new MyViewPagerAdaoter();
        mViewPager = (ViewPager) view.findViewById(R.id.store_home_view_pager);
        mGridView = (CustomGridView) view.findViewById(R.id.store_home_grid_view);
        mViewPager.setAdapter(pagerAdapter);
        gridAdapter = new MyGridAdapter();
        mGridView.setAdapter(gridAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                controlIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        initInfo("");
                        break;
                    case 1:
                        initInfo("38%2C33%2C34");
                        break;
                    case 2:
                        initInfo("39%2C40");
                        break;
                    case 3:
                        initInfo("49%2C45%2C48%2C46%2C44");
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

    /**
     * 最上面的viewpager的适配器
     */
    class MyViewPagerAdaoter extends PagerAdapter {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(context);
            Picasso.with(context).load(urlList.get(position)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position){
                        case 0:
                            Intent intent = new Intent(context, ProjectActivity.class);
                            String id = "14816";
                            intent.putExtra("topic_id", id);
                            startActivity(intent);
                            break;
                        case 1:
                            Intent intent1=new Intent(context, SnapActivity.class);
                            startActivity(intent1);
                            //TODO
                            break;
                    }
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 商城中间listview的适配器
     */

    class MyGridAdapter extends BaseAdapter {
        private void controlIndicator2(int index) {
            ImageView view = (ImageView) linearLayout.getChildAt(index);
            //初始化所有的ImageView的enable属性为false
            for (int i = 0; i < 5; i++) {
                ImageView childView = (ImageView) linearLayout.getChildAt(i);
                childView.setEnabled(false);
            }
            view.setEnabled(true);
        }
        @Override
        public int getCount() {
            return beanList.size();
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
        public View getView(final int i, View containView, ViewGroup viewGroup) {
            dataBean = beanList.get(i);
            //图片的数组
            List<StoreHomePageBean.DataBean.RegionNameBean> region_name = dataBean.getRegion_name();
            //viewpager的数组
            region_pictures = dataBean.getRegion_pictures();
            //recycleview的数组
            region_skus = dataBean.getRegion_skus();
            MyGridViewPagerAdapter viewPagerAdapter = new MyGridViewPagerAdapter(region_pictures);
            MyGridRecycleViewAdapter cycleAdapter = new MyGridRecycleViewAdapter(region_skus);
            viewPagerAdapter.notifyDataSetChanged();
            cycleAdapter.notifyDataSetChanged();
            View view = containView;
            ViewHolder viewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.store_home_page_grid_view_item, viewGroup, false);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            final StoreHomePageBean.DataBean.RegionNameBean.ComponentBean component2 = region_name.get(0).getComponent();
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            Picasso.with(context).load(component2.getPicUrl()).into(viewHolder.imageView);
            viewHolder.viewPager.setAdapter(viewPagerAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.recyclerView.setLayoutManager(layoutManager);
            viewHolder.recyclerView.setAdapter(cycleAdapter);
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GlobalActivity.class);
                    intent.putExtra("china_id",component2.getAction().getId());
                    startActivity(intent);
                }
            });
            viewHolder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    controlIndicator2(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            return view;
        }

        class ViewHolder {
            public ImageView imageView;
            public ViewPager viewPager;
            public RecyclerView recyclerView;

            public ViewHolder(View view) {
                this.imageView = (ImageView) view.findViewById(R.id.store_grid_view_img);
                this.viewPager = (ViewPager) view.findViewById(R.id.store_grid_view_view_pager);
                this.recyclerView = (RecyclerView) view.findViewById(R.id.store_grid_view_recycleview);
                linearLayout= (LinearLayout) view.findViewById(R.id.store_grid_view_linear_layout);
                view.setTag(this);
            }
        }

        /**
         * listview的item中的viewpager适配器
         */
        class MyGridViewPagerAdapter extends PagerAdapter {
            private List<StoreHomePageBean.DataBean.RegionPicturesBean> region_picture;

            public MyGridViewPagerAdapter(List<StoreHomePageBean.DataBean.RegionPicturesBean> region_pictures) {
                this.region_picture = region_pictures;
            }

            @Override
            public int getCount() {
                return region_picture == null ? 0 : region_picture.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                final StoreHomePageBean.DataBean.RegionPicturesBean.ComponentBean component = region_picture.get(position).getComponent();
//                View view=LayoutInflater.from(context).inflate(R.layout.store_home_list_view_view_pager_item,container,false);
//                ImageView imageView= (ImageView) view.findViewById(R.id.store_home_list_view_view_pager_img);
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(component.getPicUrl()).into(imageView);
                container.addView(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ProjectActivity.class);
                        String id = component.getAction().getId();
                        intent.putExtra("topic_id", id);
                        startActivity(intent);
                    }
                });
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        }

        /**
         * listviewr中的ecycleview的适配器
         */

        class RecycleViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView, currentPrice, originPrice;

            public RecycleViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.store_grid_view_recycleview_img);
                textView = (TextView) itemView.findViewById(R.id.store_grid_view_recycleview_txt);
                currentPrice = (TextView) itemView.findViewById(R.id.store_grid_view_recycleview_currentprice);
                originPrice = (TextView) itemView.findViewById(R.id.store_grid_view_recycleview_originprice);
            }
        }

        class MyGridRecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {
            private List<StoreHomePageBean.DataBean.RegionSkusBean> region_sku;

            public MyGridRecycleViewAdapter(List<StoreHomePageBean.DataBean.RegionSkusBean> region_sku) {
                this.region_sku = region_sku;
            }

            @Override
            public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(context).inflate(R.layout.store_grid_view_recycleview_item, parent, false);
                return new RecycleViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecycleViewHolder holder, int position) {
                final StoreHomePageBean.DataBean.RegionSkusBean.ComponentBean component = region_sku.get(position).getComponent();
                Picasso.with(context).load(component.getPicUrl()).into(holder.imageView);
                holder.imageView.setImageResource(R.mipmap.ic_launcher);
                holder.textView.setText(component.getTitle());
                holder.currentPrice.setText("￥" + component.getPrice());
                holder.originPrice.setText("￥" + component.getOrigin_price());
                holder.originPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, PurchaseDetails.class);
                        intent.putExtra("source_id",component.getAction().getSourceId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return region_sku == null ? 0 : region_sku.size();
            }
        }
    }

    public class StringViewHolder extends UltimateRecyclerviewViewHolder {
        public ImageView imageView;
        public TextView title, currentPrice, originPrice, countryName;
        public CircleImageView circleImageView;

        public StringViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                imageView = (ImageView) itemView.findViewById(R.id.brand_detail_ultimate_img);
                title = (TextView) itemView.findViewById(R.id.brand_detail_ultimate_title);
                currentPrice = (TextView) itemView.findViewById(R.id.brand_detail_ultimate_currentprice);
                originPrice = (TextView) itemView.findViewById(R.id.brand_detail_ultimate_originprice);
                circleImageView = (CircleImageView) itemView.findViewById(R.id.brand_detail_ultimate_circlrcountry);
                countryName = (TextView) itemView.findViewById(R.id.brand_detail_ultimate_countryname);
            }
        }
    }

    /**
     * 最下面的ultimaterecyclerview的适配器
     */
    class StringAdapter extends UltimateViewAdapter<StringViewHolder> {
        private List<StoreHomePageNextBean.DataBean.ItemsBean> items;

        public StringAdapter(List<StoreHomePageNextBean.DataBean.ItemsBean> items) {
            this.items = items;
        }

        @Override
        public StringViewHolder newFooterHolder(View view) {
            return null;
        }

        @Override
        public StringViewHolder newHeaderHolder(View view) {
            return null;
        }

        @Override
        public StringViewHolder onCreateViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.brand_detail_ultimatefresh_item, null);
            return new StringViewHolder(view, true);
        }

        @Override
        public int getAdapterItemCount() {
            return items == null ? 0 : items.size() - 1;
            //这里返回的是你的item的个数  不包括头部和加载view
        }

        @Override
        public long generateHeaderId(int position) {
            return -1;
        }

        @Override
        public void onBindViewHolder(final StringViewHolder holder, final int position) {
            component = items.get(position + 1).getComponent();
            //一定要加这个判断  因为UltimateRecyclerView本身有加了头部和尾部  这个方法返回的是包括头部和尾部在内的
            if (position < getItemCount() && (customHeaderView != null ? position <= items.size() : position < items.size()) && (customHeaderView != null ? position > 0 : true)) {
                holder.imageView.setImageResource(R.mipmap.ic_launcher);
                holder.title.setText(StoreHomePageFragment.this.component.getDescription());
                holder.currentPrice.setText("￥" + component.getPrice());
                holder.originPrice.setText("￥" + component.getOrigin_price());
                holder.countryName.setText(component.getCountry());
                holder.originPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                Picasso.with(context).load(component.getPicUrl()).into(holder.imageView);
                if (!component.getNationalFlag().equals("")) {
                    Picasso.with(context).load(component.getNationalFlag()).into(holder.circleImageView);
                }
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        int position1 = holder.getPosition();
                        component = items.get(position + 1).getComponent();
//                        items.get(position).getComponent().
                        Intent intent = new Intent(context, PurchaseDetails.class);
                        intent.putExtra("source_id","2429284");
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.brand_detail_ultimatefresh_item, null);
            return new StringViewHolder(view, true);
            //初始化item的头部布局  这里为了方便 就直接用StringViewHolder,实际使用可以使用不同于item的布局
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (customHeaderView != null) {
                position -= 1;
            }
        }
    }
}
