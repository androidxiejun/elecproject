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
            DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, "android");
            SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
            DaoMaster daoMaster = new DaoMaster(readableDatabase);
            DaoSession daoSession = daoMaster.newSession();
            attentionDao = daoSession.getAttentionDao();
        }
        return attentionDao;
    }
}
