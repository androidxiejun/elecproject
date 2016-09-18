package com.example.administrator.electronicproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by sunbin on 2016/9/17.
 * 消息界面的通知
 */
public class MessageNotifactionFragment extends Fragment {
    private Context context;
    private PullToRefreshListView pullListView;
    private RelativeLayout emptyView;
    private ListView refreshableView;

    public static MessageNotifactionFragment newInstance(){
        return new MessageNotifactionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.message_notification_layout, container, false);
        pullListView = (PullToRefreshListView) inflate.findViewById(R.id.message_notification_pull);
        emptyView = (RelativeLayout) inflate.findViewById(R.id.message_notification_empty);

        refreshableView = pullListView.getRefreshableView();
        refreshableView.setEmptyView(emptyView);
        return inflate;
    }
}
