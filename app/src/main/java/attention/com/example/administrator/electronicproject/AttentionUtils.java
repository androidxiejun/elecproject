package attention.com.example.administrator.electronicproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sunbin on 2016/9/18.
 */
public class AttentionUtils {

    public static AttentionDao attentionDao;

    public static AttentionDao getDao(Context context){
        if (attentionDao == null){
            AttentiondbDaoMaster.DevOpenHelper openHelper = new AttentiondbDaoMaster.DevOpenHelper(context, "android");
            SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
            AttentiondbDaoMaster attentiondbDaoMaster = new AttentiondbDaoMaster(readableDatabase);
            AttentiondbDaoSession attentiondbDaoSession = attentiondbDaoMaster.newSession();
            attentionDao = attentiondbDaoSession.getAttentionDao();
        }
        return attentionDao;
    }
}
