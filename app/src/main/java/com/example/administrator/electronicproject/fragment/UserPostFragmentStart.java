package com.example.administrator.electronicproject.fragment;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.electronicproject.R;

/**
 * Created by sunbin on 2016/9/14.
 * 启动欢迎动画
 */
public class UserPostFragmentStart extends Fragment {

    private Context context;
    private ImageView anim;

    public static UserPostFragmentStart newInstance(){
        return new UserPostFragmentStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.start_anim, container, false);
        anim = (ImageView) inflate.findViewById(R.id.start_animotion);
        AnimationDrawable drawable = (AnimationDrawable) anim.getDrawable();
        drawable.start();
        return inflate;
    }
}
