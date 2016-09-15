package com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapBean.SnapingBean;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapTimeActivity;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapUtils.SnapHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/13.
 */
public class SnapingFragment extends Fragment {
    private Context context;
    private PullToRefreshListView mListView;
    private MyListAdapter adapter;
    private List<SnapingBean.DataBean.ItemsBean> items;
    public static SnapingFragment newInstance(){
        return new SnapingFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.snap_snaping_fragment,container,false);
        initView(view);
        initInfo();
        return view;
    }

    private void initView(View view) {
        View view2=LayoutInflater.from(context).inflate(R.layout.snap_foot_item,null);
        mListView= (PullToRefreshListView) view.findViewById(R.id.snap_pull_to_list_view);
        adapter=new MyListAdapter();
        ListView refreshableView = mListView.getRefreshableView();
        refreshableView.addFooterView(view2);
        mListView.setAdapter(adapter);
    }

    private void initInfo() {
        SnapHttpUtils.create().querySnapingBean().enqueue(new Callback<SnapingBean>() {
            @Override
            public void onResponse(Call<SnapingBean> call, Response<SnapingBean> response) {
                 items= response.body().getData().getItems();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SnapingBean> call, Throwable t) {

            }
        });
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
            final SnapingBean.DataBean.ItemsBean.ComponentBean component = items.get(i).getComponent();
            View view = view2;
            ViewHolder viewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.snap_list_view_item, viewGroup, false);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Picasso.with(context).load(component.getPicUrl()).into(viewHolder.imageView);
            viewHolder.title.setText(component.getTitle());
            viewHolder.account.setText(component.getSaleNum());
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, SnapTimeActivity.class);
                    intent.putExtra("tuan_id",component.getId());
                    startActivity(intent);
                }
            });
            return view;
        }

        class ViewHolder {
            public ImageView imageView;
            public TextView title, account;
            public ViewHolder(View view) {
                view.setTag(this);
                this.imageView = (ImageView) view.findViewById(R.id.snap_list_view_img);
                this.title = (TextView) view.findViewById(R.id.snap_list_view_title);
                this.account = (TextView) view.findViewById(R.id.snap_list_view_account);
            }
        }
    }
}
