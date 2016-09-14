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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/12.
 */
public class RetrieveFragmentTwo extends Fragment implements View.OnClickListener{

    @BindView(R.id.retrieve_fragment_two_next)
    TextView nextTv;
    @BindView(R.id.retrieve_fragment_two_image_one)
    ImageView one;
    @BindView(R.id.retrieve_fragment_two_image_two)
    ImageView two;
    @BindView(R.id.retrieve_fragment_two_image_three)
    ImageView three;
    @BindView(R.id.retrieve_fragment_two_image_four)
    ImageView four;
    @BindView(R.id.retrieve_fragment_two_get_user_code)
    TextView getCode;
    @BindView(R.id.retrieve_fragment_two_user_code)
    EditText code;
    @BindView(R.id.retrieve_fragment_two_user_pass)
    EditText newPass;
    @BindView(R.id.retrieve_fragment_two_user_register)
    TextView ensure;

    private Context context;

    public static RetrieveFragmentTwo newInstances(){
        return new RetrieveFragmentTwo();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.retrieve_fragment_two, container, false);
        ButterKnife.bind(this,inflate);
        initView();
        return inflate;
    }

    private void initView() {
        nextTv.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        getCode.setOnClickListener(this);
        ensure.setOnClickListener(this);
        ensure.setClickable(false);//默认点击无效

        //当新密码中有数据时，可以点击
        newPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (newPass.getText().length() > 0){
                    ensure.setBackgroundColor(Color.RED);
                    ensure.setClickable(true);
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
            case R.id.retrieve_fragment_two_next://换一个
                break;
            case R.id.retrieve_fragment_two_image_one://图片的点击动画
                break;
            case R.id.retrieve_fragment_two_image_two://图片的点击动画
                break;
            case R.id.retrieve_fragment_two_image_three://图片的点击动画
                break;
            case R.id.retrieve_fragment_two_image_four://图片的点击动画
                break;
            case R.id.retrieve_fragment_two_get_user_code://获取验证码
                break;
            case R.id.retrieve_fragment_two_user_register://完成
                break;
        }
    }
}
