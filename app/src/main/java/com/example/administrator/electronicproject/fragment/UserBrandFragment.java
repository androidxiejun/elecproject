package com.example.administrator.electronicproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by sunbin on 2016/9/14.
 */
public class UserBrandFragment extends Fragment{

    private Context context;
    private PullToRefreshListView pullList;
    private ListView refreshableView;
    private TextView empty;

    public static UserBrandFragment newInstance(){
        return new UserBrandFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.user_post_fargment, container, false);
        pullList = (PullToRefreshListView) inflate.findViewById(R.id.user_post_pull_list);
        empty = (TextView) inflate.findViewById(R.id.user_post_empty);
        refreshableView = pullList.getRefreshableView();
        refreshableView.setEmptyView(empty);
        pullList.setMode(PullToRefreshBase.Mode.BOTH);
        return inflate;
    }
}
