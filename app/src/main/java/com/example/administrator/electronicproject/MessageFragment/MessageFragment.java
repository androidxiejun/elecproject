package com.example.administrator.electronicproject.MessageFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.fragment.MessageItemFragment;

/**
 * Created by Administrator on 2016/9/5.
 */
public class MessageFragment extends Fragment {

    private Context context;
    private FragmentManager manager;
    private Fragment mCurrentShowFragment;
    private MessageItemFragment messageItemFragment;

    public static MessageFragment newInstance(){
        return new MessageFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        manager = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.message_layout, container, false);

        messageItemFragment = MessageItemFragment.newInstance();
        chooseFragment(messageItemFragment);
        return inflate;
    }


    /**
     * 当点击RadioButton时会执行此方法，加载相应的fragment视图
     * @param fragment
     */
    private void chooseFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (mCurrentShowFragment != null && mCurrentShowFragment.isAdded()) {
            fragmentTransaction.hide(mCurrentShowFragment);
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.message_feame_layout,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }
}
