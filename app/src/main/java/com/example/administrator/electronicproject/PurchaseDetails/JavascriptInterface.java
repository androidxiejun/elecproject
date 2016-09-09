package com.example.administrator.electronicproject.PurchaseDetails;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yangjw on 2016/8/12.
 */
public class JavascriptInterface {

    private Context context;

    public JavascriptInterface(Context context) {
        this.context = context;
    }

    /**
     * 在Html中要调用次方法。使用JavascriptInterface注解定位当前方法为javascript的接口
     * @param name
     * @param pwd
     */
    @android.webkit.JavascriptInterface
    public void login(String name,String pwd) {
        Toast.makeText(context, name+"::::"+pwd, Toast.LENGTH_SHORT).show();
    }

    @android.webkit.JavascriptInterface
    public void startComicDetail(int id) {
        Toast.makeText(context, "" + id, Toast.LENGTH_SHORT).show();
    }
}
