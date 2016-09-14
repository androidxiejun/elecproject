package com.example.administrator.electronicproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
 * Created by sunbin on 2016/9/13.
 * 用户邮箱和地址
 */
public class UserEmailActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.user_email_back_btn)
    Button backBtn;
    @BindView(R.id.user_email_title_tv)
    TextView title;
    @BindView(R.id.user_email_ensure_btn)
    Button ensureBtn;
    @BindView(R.id.user_email_et)
    EditText content;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private String come;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_email);
        ButterKnife.bind(this);

        intent = getIntent();
        come = intent.getStringExtra("come");
        if(come.equals("email")){
            sharedPreferences = getSharedPreferences("email",MODE_PRIVATE);
            content.setText(sharedPreferences.getString("user_email",null));
            title.setText("邮箱");
        }else if (come.equals("address")){
            sharedPreferences = getSharedPreferences("address",MODE_PRIVATE);
            content.setText(sharedPreferences.getString("user_address",null));
            title.setText("地址");
        }
        edit = sharedPreferences.edit();
        initView();
    }

    private void initView() {
        content.setSelection(content.getText().length());
        backBtn.setOnClickListener(this);
        ensureBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_email_back_btn:
                finish();
                break;
            case R.id.user_email_ensure_btn:
                if(come.equals("email")){
                    edit.putString("user_email",content.getText().toString());
                }else if (come.equals("address")){
                    edit.putString("user_address",content.getText().toString());
                }
                edit.commit();
                finish();
                break;
        }
    }
}
