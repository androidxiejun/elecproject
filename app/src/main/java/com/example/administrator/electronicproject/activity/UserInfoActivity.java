package com.example.administrator.electronicproject.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sunbin on 2016/9/12.
 * 个人信息界面
 */
public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.user_info_back_btn)
    Button backBtn;
    @BindView(R.id.user_info_ensure_btn)
    Button ensureBtn;
    @BindView(R.id.user_info_head_image)
    CircleImageView headImage;
    @BindView(R.id.user_info_nick_name)
    RelativeLayout nickName;
    @BindView(R.id.user_info_nick_content)
    TextView nickContent;
    @BindView(R.id.user_info_sex)
    RelativeLayout userSex;
    @BindView(R.id.user_info_sex_content)
    TextView sexContent;
    @BindView(R.id.user_info_id)
    RelativeLayout userId;
    @BindView(R.id.user_info_birthday)
    RelativeLayout userBirthday;
    @BindView(R.id.user_info_brithday_content)
    TextView birthdayContent;
    @BindView(R.id.user_info_mobile)
    RelativeLayout userMobile;
    @BindView(R.id.user_info_mobile_content)
    TextView mobileContent;
    @BindView(R.id.user_info_email)
    RelativeLayout userEmail;
    @BindView(R.id.user_info_address)
    RelativeLayout userAddress;
    private Context context;

    //点击头像出现的弹出框
    private PopupWindow headImagePopup;
    private TextView tackPhoto;
    private TextView choosePhoto;
    private TextView caclePhoto;

    //性别选择
    private PopupWindow sexPopup;
    private TextView man;
    private TextView woman;
    private TextView sexCacle;

    //生日选择
    private DatePicker datePick;
    private TextView birthdayEnsure;
    private static int year = 0;
    private static int mouth = 0;
    private static int day = 0;
    private PopupWindow birthdayPopup;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_layout);
        ButterKnife.bind(this);

        context = this;
        sharedPreferences = getSharedPreferences("birthday",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        initListener();
    }

    private void initListener() {
        backBtn.setOnClickListener(this);
        ensureBtn.setOnClickListener(this);
        headImage.setOnClickListener(this);
        nickName.setOnClickListener(this);
        userSex.setOnClickListener(this);
        userBirthday.setOnClickListener(this);
        userMobile.setOnClickListener(this);
        userEmail.setOnClickListener(this);
        userAddress.setOnClickListener(this);

        year = sharedPreferences.getInt("year",Calendar.YEAR);
        mouth = sharedPreferences.getInt("mouth",Calendar.MONTH);
        day = sharedPreferences.getInt("day",Calendar.DAY_OF_MONTH);
        birthdayContent.setText(year+"-"+mouth+"-"+day);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_info_back_btn://返回
                finish();
                break;
            case R.id.user_info_ensure_btn://确定
                //保存更改，并退出
                break;
            case R.id.user_info_head_image://头像
                initHeadImagePopup();
                break;
            case R.id.user_info_nick_name://昵称
                Intent nick = new Intent(this,UserNickActivity.class);
                nick.putExtra("hint",nickContent.getText());
                startActivity(nick);
                break;
            case R.id.user_info_sex://性别
                initSexPopup();
                break;
            case R.id.user_info_birthday://生日
                brithdyPopup();
                break;
            case R.id.user_info_mobile://手机
                Intent mobile = new Intent(this,UserInfoMobileActivity.class);
                mobile.putExtra("mobile",Integer.valueOf(mobileContent.getText().toString()));
                startActivity(mobile);
                break;
            case R.id.user_info_email://邮箱
                Intent email = new Intent(this,UserEmailActivity.class);
                email.putExtra("come","email");
                startActivity(email);
                break;
            case R.id.user_info_address://地址
                Intent address = new Intent(this,UserEmailActivity.class);
                address.putExtra("come","address");
                startActivity(address);
                break;
        }
    }

    /**
     * 点击头像出现的弹出框
     */
    private void initHeadImagePopup() {
        View popup = LayoutInflater.from(this).inflate(R.layout.user_info_popup, null,false);

        tackPhoto = (TextView) popup.findViewById(R.id.user_info_popup_tack_photo);
        choosePhoto = (TextView) popup.findViewById(R.id.user_info_popup_choose_photo);
        caclePhoto = (TextView) popup.findViewById(R.id.user_info_popup_cancel);
        tackPhoto.setOnClickListener(popupListener);
        caclePhoto.setOnClickListener(popupListener);
        choosePhoto.setOnClickListener(popupListener);

        headImagePopup = new PopupWindow(popup, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        headImagePopup.setBackgroundDrawable(new ColorDrawable());
        headImagePopup.setOutsideTouchable(true);
        headImagePopup.showAtLocation(popup,Gravity.BOTTOM,0,0);
    }

    /**
     * 点击头像出现的弹出框的点击事件
     */
    private View.OnClickListener popupListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.user_info_popup_tack_photo://拍照
//                    Intent intent = new Intent(context,TackPhotoActivity.class);
//                    startActivityForResult(intent,1);
                    break;
                case R.id.user_info_popup_choose_photo://从相册选取照片
//                    Intent intent = new Intent();
//                    intent.setAction("android.media.action.IMAGE_CAPTURE");
//                    startActivityForResult(intent, 2);
                    break;
                case R.id.user_info_popup_cancel://取消
                    headImagePopup.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(data.getStringExtra("path"));
            headImage.setImageBitmap(bitmap);
        }
    }

    /**
     * 性别选择
     */
    private void initSexPopup(){
        View sex = LayoutInflater.from(this).inflate(R.layout.user_sex_layout, null,false);

        man = (TextView) sex.findViewById(R.id.user_info_popup_sex_man);
        woman = (TextView) sex.findViewById(R.id.user_info_popup_sex_woman);
        sexCacle = (TextView) sex.findViewById(R.id.user_sex_popup_cancel);
        man.setOnClickListener(sexListener);
        woman.setOnClickListener(sexListener);
        sexCacle.setOnClickListener(sexListener);

        sexPopup = new PopupWindow(sex, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        sexPopup.setBackgroundDrawable(new ColorDrawable());
//        sexPopup.setOutsideTouchable(true);
        sexPopup.showAtLocation(sex,Gravity.BOTTOM,0,0);
    }

    /**
     * 性别选择出现的弹出框的点击事件
     */
    private View.OnClickListener sexListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.user_info_popup_sex_man://男
                    sexContent.setText(man.getText());
                    break;
                case R.id.user_info_popup_sex_woman://女
                    sexContent.setText(woman.getText());
                    break;
                case R.id.user_sex_popup_cancel://取消
                    sexPopup.dismiss();
                    break;
            }
        }
    };

    /**
     * 生日选择的弹出框
     */
    private void brithdyPopup(){
        final Calendar calendar = Calendar.getInstance();
        View birthday = LayoutInflater.from(this).inflate(R.layout.user_birthday_layout, null, false);
        birthdayPopup = new PopupWindow(birthday, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        datePick = (DatePicker) birthday.findViewById(R.id.user_brithday_date);
        birthdayEnsure = (TextView) birthday.findViewById(R.id.user_birthday_ensure);
        datePick.init(year, mouth, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int years, int mouths, int days) {
                //日期改变的监听
                year = years;
                mouth = mouths;
                day = days;
            }
        });
        birthdayEnsure.setOnClickListener(birthdayListener);

        birthdayPopup.setBackgroundDrawable(new ColorDrawable());
        birthdayPopup.setOutsideTouchable(true);
        birthdayPopup.showAtLocation(birthday,Gravity.BOTTOM,0,0);
    }

    private View.OnClickListener birthdayListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edit.putInt("year",year);
            edit.putInt("mouth",mouth);
            edit.putInt("day",day);
            birthdayContent.setText(year+"-"+mouth+"-"+day);
            birthdayPopup.dismiss();
        }
    };
}
