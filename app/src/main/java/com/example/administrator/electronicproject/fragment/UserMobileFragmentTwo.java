package com.example.administrator.electronicproject.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/13.
 * 绑定手机号第二个界面
 */
public class UserMobileFragmentTwo extends Fragment implements View.OnClickListener{

    @BindView(R.id.user_mobile_new_mobile)
    EditText newMobile;
    @BindView(R.id.user_mobile_new_get_code)
    TextView getNewCode;
    @BindView(R.id.user_mobile_new_code)
    EditText newCode;
    @BindView(R.id.user_mobile_new_next)
    TextView ensure;

    private Context context;

    public static UserMobileFragmentTwo newInstance(){
        return new UserMobileFragmentTwo();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.user_mobile_two, container, false);
        ButterKnife.bind(this,inflate);
        initListener();
        return inflate;
    }

    private void initListener() {
        getNewCode.setOnClickListener(this);
        ensure.setOnClickListener(this);
        ensure.setClickable(false);

        newCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (newCode.getText().length() > 0){
                    ensure.setBackgroundColor(Color.RED);
                    ensure.setClickable(true);
                }else {
                    ensure.setBackgroundColor(Color.GRAY);
                    ensure.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_mobile_new_get_code://获取验证码
                break;
            case R.id.user_mobile_new_next://确定

                break;
        }
    }
}
