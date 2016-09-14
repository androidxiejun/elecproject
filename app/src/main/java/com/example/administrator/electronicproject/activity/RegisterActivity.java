package com.example.administrator.electronicproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/12.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.register_back_btn)
    Button backBtn;
    @BindView(R.id.register_user_phone)
    EditText userPhone;
    @BindView(R.id.register_get_user_code)
    TextView getCode;
    @BindView(R.id.register_user_code)
    EditText userCode;
    @BindView(R.id.register_user_pass)
    EditText userPass;
    @BindView(R.id.register_user_register)
    TextView ensureRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        backBtn.setOnClickListener(this);
        getCode.setOnClickListener(this);
        ensureRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_back_btn:
                finish();
                break;
            case R.id.register_get_user_code://获取验证码
                break;
            case R.id.register_user_register://确认注册
                break;
        }
    }
}
