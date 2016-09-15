package com.example.administrator.electronicproject.WelcomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.electronicproject.MainActivity;
import com.example.administrator.electronicproject.R;

public class WelcomeActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_activity_two);
        mHandler.sendEmptyMessageDelayed(1,2000);
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Intent intent=new Intent(WelcomeActivityTwo.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
