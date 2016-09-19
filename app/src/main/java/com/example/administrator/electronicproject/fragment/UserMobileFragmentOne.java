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
 * 绑定手机号的第一个fragment
 */
public class UserMobileFragmentOne extends Fragment implements View.OnClickListener{

    @BindView(R.id.user_mobile_now_content)
    TextView mobileContent;
    @BindView(R.id.user_mobile_get_code)
    TextView getCode;
    @BindView(R.id.user_mobile_code)
    EditText code;
    @BindView(R.id.user_mobile_next)
    TextView next;

    private Context context;
    private static String mobile;
    private static MobileCallBack callBack;


    public interface MobileCallBack{
        void addNext();
    }

    public static UserMobileFragmentOne newInstance(String mobiles,MobileCallBack call){
        mobile = mobiles;
        callBack = call;
        return new UserMobileFragmentOne();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.user_mobile_one, container, false);
        ButterKnife.bind(this,inflate);

        mobileContent.setText(mobile+"");
        initLstener();
        return inflate;
    }

    private void initLstener() {
        getCode.setOnClickListener(this);
        next.setOnClickListener(this);
        next.setClickable(false);//默认不可点击

        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (code.getText().length() > 0){
                    next.setBackgroundColor(Color.RED);
                    next.setClickable(true);
                }else {
                    next.setBackgroundColor(Color.GRAY);
                    next.setClickable(false);
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
            case R.id.user_mobile_get_code://获取验证码
                //这里写一个倒计时
                break;
            case R.id.user_mobile_next://下一步
                callBack.addNext();
                break;
        }
    }
}
