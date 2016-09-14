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

/**
 * Created by sunbin on 2016/9/12.
 */
public class RetrieveFragmentOne extends Fragment implements View.OnClickListener{

    private Context context;
    private EditText userPhone;
    private TextView ensureRegister;
    private static RetrieveCallBackOne callBackOne;

    public interface RetrieveCallBackOne{
        void callBack();
    }

    public static RetrieveFragmentOne newInstance(RetrieveCallBackOne callBack){
        callBackOne = callBack;
        return new RetrieveFragmentOne();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.retrieve_fragment_one, container, false);
        userPhone = (EditText) inflate.findViewById(R.id.register_fragment_one_user_phone);
        ensureRegister = (TextView) inflate.findViewById(R.id.register_fragment_one_user_register);
        ensureRegister.setOnClickListener(this);
        ensureRegister.setClickable(false);
        //用于改变下一步按钮的颜色，和是否可以点击
        userPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (userPhone.getText().length() > 0){
                    ensureRegister.setBackgroundColor(Color.RED);
                    ensureRegister.setClickable(true);
                }else {
                    ensureRegister.setBackgroundColor(Color.GRAY);
                    ensureRegister.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return inflate;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_fragment_one_user_register:
                callBackOne.callBack();
                break;
        }
    }
}
