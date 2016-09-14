package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.electronicproject.FashionFragment.view.activity.ExpertActivity;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

/**
 * Created by sunbin on 2016/9/9.
 */
public class FashionAttentionFragment extends Fragment {

    private Context context;
    private PullToRefreshGridView mAttentionGridView;
    private Button mBtn;

    public static FashionAttentionFragment newInstance(){
        return new FashionAttentionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fashion_attention_first, container, false);
        mBtn = (Button) view.findViewById(R.id.fashion_attention_welcome_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExpertActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
