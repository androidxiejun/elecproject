package com.example.administrator.electronicproject.SearchActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.electronicproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.search_back_btn)
    Button backBtn;
    @BindView(R.id.search_tab_layout)
    TabLayout mTablayout;
    @BindView(R.id.search_edit_text)
    EditText mEditText;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context=this;
        ButterKnife.bind(this);
        initListenner();
    }

    private void initListenner() {
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
          finish();
    }
}
