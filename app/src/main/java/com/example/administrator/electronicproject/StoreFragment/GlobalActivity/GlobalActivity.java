package com.example.administrator.electronicproject.StoreFragment.GlobalActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.electronicproject.R;

public class GlobalActivity extends AppCompatActivity {
    private Context context;
    private GlobalFragment fragment;
    private FragmentManager manager;
    public static String china_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);
        context = this;
        initIntent();
        initFragment();
    }

    private void initIntent() {
        Intent intent = getIntent();
        china_id=intent.getStringExtra("china_id");
    }

    public void onClick(View view) {
        finish();
    }

    private void initFragment() {
        fragment = GlobalFragment.newInstance();
        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.store_global_fragment, fragment);
        fragmentTransaction.commit();
    }
}
