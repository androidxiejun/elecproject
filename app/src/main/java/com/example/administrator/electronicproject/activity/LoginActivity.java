package com.example.administrator.electronicproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.electronicproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/12.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.login_back_btn)
    Button backBtn;
    @BindView(R.id.login_user_phone_number)
    EditText userPhone;
    @BindView(R.id.login_user_pass_word)
    EditText userPass;
    @BindView(R.id.login_ensure_btn)
    Button ensureLogin;
    @BindView(R.id.login_newuser_register)
    TextView newUser;
    @BindView(R.id.login_forget_pass)
    TextView forgetPass;
    @BindView(R.id.login_taobao)
    TextView taobao;
    @BindView(R.id.login_qq)
    TextView qq;
    @BindView(R.id.login_weibo)
    TextView weibo;
    @BindView(R.id.login_wechat)
    TextView weChat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        backBtn.setOnClickListener(this);
        ensureLogin.setOnClickListener(this);
        newUser.setOnClickListener(this);
        forgetPass.setOnClickListener(this);
        taobao.setOnClickListener(this);
        qq.setOnClickListener(this);
        weibo.setOnClickListener(this);
        weChat.setOnClickListener(this);

        //当密码长度大于6位时，登陆按钮变红
        userPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (userPass.getText().length() >= 6){
                    userPass.setBackgroundColor(Color.RED);
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
            case R.id.login_back_btn://返回
                finish();
                break;
            case R.id.login_ensure_btn://确认登陆
                if (userPhone.getText().length() < 11 || userPass.getText().length() < 6){
                    Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                    break;
                }
                //登陆成功，保存登陆信息 。。。
                break;
            case R.id.login_newuser_register://新用户注册
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_forget_pass://忘记密码,找回密码界面
                Intent intentForPass = new Intent(this,RetrievePassActivity.class);
                startActivity(intentForPass);
                break;
            case R.id.login_taobao://淘宝登陆

                break;
            case R.id.login_qq://qq登陆
                break;
            case R.id.login_weibo://微博登陆
                break;
            case R.id.login_wechat://微信登陆
                break;
        }
    }
}
