package com.example.administrator.electronicproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.electronicproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/13.
 * 用户昵称界面
 */
public class UserNickActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.user_nick_back_btn)
    Button backBtn;
    @BindView(R.id.user_nick_ensure_btn)
    Button ensureBtn;
    @BindView(R.id.user_nick_et)
    EditText nick;
    private Intent intent;
    private String nickContent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_nick_layout);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences("nick",MODE_PRIVATE);
        edit = sharedPreferences.edit();

        intent = getIntent();
        nickContent = intent.getStringExtra("hint");
        initView();
    }

    private void initView() {
        nick.setHint(nickContent);

        backBtn.setOnClickListener(this);
        ensureBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_nick_back_btn://返回
                finish();
                break;
            case R.id.user_nick_ensure_btn://保存信息，并退出
                edit.putString("nick",nick.getText().toString());
                edit.commit();
                finish();
                break;
        }
    }
}
