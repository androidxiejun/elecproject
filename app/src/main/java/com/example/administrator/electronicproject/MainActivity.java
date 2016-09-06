package com.example.administrator.electronicproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.administrator.electronicproject.ClassifyFragment.ClassifyFragment;
import com.example.administrator.electronicproject.FashionFragment.view.FashionFragment;
import com.example.administrator.electronicproject.MessageFragment.MessageFragment;
import com.example.administrator.electronicproject.MineFragment.MineFragment;
import com.example.administrator.electronicproject.StoreFragment.StoreFragment;

public class MainActivity extends AppCompatActivity {
    private RadioGroup mRadioGroup;
    private StoreFragment storeFragment;
    private ClassifyFragment classifyFragment;
    private FashionFragment fashionFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private FragmentManager manager;
    private Fragment mCurrentShowFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        chooseRadioBtn();
        chooseFragment(fashionFragment);
    }

    /**
     * 对fragment进行初始化
     */
    private void initFragment() {
        storeFragment=StoreFragment.newInstance();
        classifyFragment=ClassifyFragment.newInstance();
        fashionFragment=FashionFragment.newInstance();
        messageFragment=MessageFragment.newInstance();
        mineFragment=MineFragment.newInstance();
    }

    /**
     * 对控件进行初始化
     */
    private void initView() {
        mRadioGroup= (RadioGroup) findViewById(R.id.radio_group);
        manager=getSupportFragmentManager();
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
            fragmentTransaction.add(R.id.frame_layout,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }
    /**
     * 对RadioGroup行点击监听，选择不同的fragment
     */
    private void chooseRadioBtn() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_button_home:
                       chooseFragment(storeFragment);
                        break;
                    case R.id.radio_button_divider:
                        chooseFragment(classifyFragment);
                        break;
                    case R.id.radio_button_fashion:
                        chooseFragment(fashionFragment);
                        break;
                    case R.id.radio_button_message:
                        chooseFragment(messageFragment);
                        break;
                    case R.id.radio_button_mine:
                        chooseFragment(mineFragment);
                        break;
                }
            }
        });
    }
}
