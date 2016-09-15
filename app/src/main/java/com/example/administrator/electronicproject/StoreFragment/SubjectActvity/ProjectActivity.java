package com.example.administrator.electronicproject.StoreFragment.SubjectActvity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.DetailBrandActivity;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.StoreFragment.CustomGridView.CustomGridView;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalActivity;
import com.example.administrator.electronicproject.StoreFragment.SubjectActvity.BigImageActivity.BigImageActvity;
import com.example.administrator.electronicproject.StoreFragment.SubjectActvity.SubjectBean.SubjectBean;
import com.example.administrator.electronicproject.StoreFragment.SubjectActvity.SubjectBean.SubjectCommonBean;
import com.example.administrator.electronicproject.StoreFragment.SubjectActvity.SubjectUtils.SubjectHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private int topic_id;
    private int index;
    private String flag;
    private Button backBtn;
    private CustomGridView mListView;
    private TextView commomTxt, lookTxt, collectTxt, titleTxt;
    private PullToRefreshListView mRefreshListView;
    private SubjectBean.DataBean data;
    private MyListAdapter mListAdapter;
    private MyFootListAdapter footAdapter;
    private List<SubjectBean.DataBean.ItemsBean> items;
    private List<SubjectBean.DataBean.ItemsBean.CellsBean> cells;
    private List<SubjectCommonBean.DataBean.ItemsBean> commonItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        context = this;
        initIntent();
        initView();
        initInfo(topic_id);
        initFootInfo(index * 18, topic_id);
        initRefreshListenner();
    }

    private void initRefreshListenner() {
        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });
    }

    private void initFootInfo(int flag, int id) {
        SubjectHttpUtils.create().queryCommonBean(flag, id).enqueue(new Callback<SubjectCommonBean>() {
            @Override
            public void onResponse(Call<SubjectCommonBean> call, Response<SubjectCommonBean> response) {
                if (response.body().getData().getFlag() != null) {
                    index++;
                } else {
                    index = 100;
                }
                commonItems.addAll(response.body().getData().getItems());
                footAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SubjectCommonBean> call, Throwable t) {

            }
        });
    }

    private class GetDataTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            initFootInfo(index * 18, topic_id);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean lean) {
            super.onPostExecute(lean);
            mRefreshListView.onRefreshComplete();
        }
    }

    private void initIntent() {
        Intent intent = getIntent();
        topic_id = Integer.valueOf(intent.getStringExtra("topic_id"));
    }

    private void initInfo(int id) {
        SubjectHttpUtils.create().queryBean(id).enqueue(new Callback<SubjectBean>() {
            @Override
            public void onResponse(Call<SubjectBean> call, Response<SubjectBean> response) {
                items = response.body().getData().getItems();
                data = response.body().getData();
                titleTxt.setText(data.getTitle());
                commomTxt.setText(data.getCommentCount());
                lookTxt.setText(data.getV());
                collectTxt.setText(data.getCollectionCount());
                mListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SubjectBean> call, Throwable t) {

            }
        });
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_list_view_head_item, null);
        View view2 = LayoutInflater.from(context).inflate(R.layout.subject_list_view_foot, null);
        backBtn= (Button) findViewById(R.id.subject_back_btn);
        backBtn.setOnClickListener(this);
        mListView = (CustomGridView) view2.findViewById(R.id.subject_foot_list_view);
        footAdapter = new MyFootListAdapter();
        mListView.setAdapter(footAdapter);
        titleTxt = (TextView) view.findViewById(R.id.subject_title);
        commomTxt = (TextView) view.findViewById(R.id.subject_common);
        lookTxt = (TextView) view.findViewById(R.id.subject_look);
        collectTxt = (TextView) view.findViewById(R.id.subject_collect);
        mRefreshListView = (PullToRefreshListView) findViewById(R.id.subject_pull_to_refresh);
        mListAdapter = new MyListAdapter();
        ListView refreshableView = mRefreshListView.getRefreshableView();
        refreshableView.addHeaderView(view);
        refreshableView.addFooterView(view2);
        mRefreshListView.setAdapter(mListAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.subject_back_btn:
                finish();
                break;
            case R.id.subject_msg:
                //TODO
                break;
            case R.id.subject_more:
                //TODO
                break;
        }
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
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
        public View getView(int i, View view2, ViewGroup viewGroup) {
            cells = items.get(i).getCells();
            GridViewAdapter adapter = new GridViewAdapter(cells);
            View view = view2;
            ViewHolder viewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.subject_list_view_item, viewGroup, false);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.gridView.setNumColumns(cells.size());
            viewHolder.gridView.setAdapter(adapter);
            return view;
        }

        class ViewHolder {
            public GridView gridView;

            public ViewHolder(View view) {
                this.gridView = (GridView) view.findViewById(R.id.subject_grid_view);
                view.setTag(this);
            }
        }

        class GridViewAdapter extends BaseAdapter {
            private List<SubjectBean.DataBean.ItemsBean.CellsBean> cell;

            public GridViewAdapter(List<SubjectBean.DataBean.ItemsBean.CellsBean> cells) {
                this.cell = cells;
            }

            @Override
            public int getCount() {
                return cell == null ? 0 : cell.size();
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
            public View getView(int i, View view2, ViewGroup viewGroup) {
                final SubjectBean.DataBean.ItemsBean.CellsBean.ComponentBean component = cell.get(i).getComponent();

                View view = view2;
                ViewHolder viewHolder = null;
                if (view == null) {
                    view = LayoutInflater.from(context).inflate(R.layout.subject_list_view_grid_item, viewGroup, false);
                    viewHolder = new ViewHolder(view);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                Picasso.with(context).load(component.getPicUrl()).into(viewHolder.imageView);
                ViewGroup.LayoutParams layoutParams = viewHolder.imageView.getLayoutParams();
                layoutParams.height = (int) cell.get(i).getHeight();
                viewHolder.imageView.setLayoutParams(layoutParams);
                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String actionType = component.getAction().getActionType();

                        if (actionType.equals("showBigPic")) {
                            //显示大图界面
                            Intent intent = new Intent(context, BigImageActvity.class);
                            intent.putExtra("picUrl",component.getPicUrl());
                            startActivity(intent);
                        } else if (actionType.equals("FAKE")) {
//                            //跳转至购买商品界面
                            Intent intent = new Intent(context, BigImageActvity.class);
                            intent.putExtra("picUrl",component.getPicUrl());
                            startActivity(intent);
                        } else if (actionType.equals("ecshopSearch")) {
                            //跳转至品牌界面
                            Intent intent = new Intent(context, DetailBrandActivity.class);
                            intent.putExtra("business_id",component.getAction().getId());
                            startActivity(intent);
                        } else {
                            //跳转至逛全球界面
                            Intent intent = new Intent(context, GlobalActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                return view;
            }

            class ViewHolder {
                public ImageView imageView;

                public ViewHolder(View view) {
                    view.setTag(this);
                    this.imageView = (ImageView) view.findViewById(R.id.subject_list_view_grid_img);
                }
            }
        }
    }

    class MyFootListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return commonItems == null ? 0 : commonItems.size();
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
        public View getView(int i, View view2, ViewGroup viewGroup) {
            SubjectCommonBean.DataBean.ItemsBean.ComponentBean component = commonItems.get(i).getComponent();
            View view = view2;
            ViewHoder viewHoder = null;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.subject_foot_list_view_item, viewGroup, false);
                viewHoder = new ViewHoder(view);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            Picasso.with(context).load(component.getUserAvatar()).into(viewHoder.footCircleImg);
            viewHoder.footFlour.setText(component.getFloor());
            viewHoder.footName.setText(component.getUserName());
            viewHoder.footTime.setText(component.getPublishDate());
            viewHoder.footCommon.setText(component.getDescription().get(0).getComponent().getText());
            return view;
        }

        class ViewHoder {
            public CircleImageView footCircleImg;
            public TextView footName, footTime, footCommon, footFlour;

            public ViewHoder(View view) {
                view.setTag(this);
                footCircleImg = (CircleImageView) view.findViewById(R.id.subject_foot_circlrcountry);
                footName = (TextView) view.findViewById(R.id.subject_foot_common_name);
                footTime = (TextView) view.findViewById(R.id.subject_foot_common_time);
                footCommon = (TextView) view.findViewById(R.id.subject_foot_commom);
                footFlour = (TextView) view.findViewById(R.id.subject_common_flour);
            }
        }
    }
}
