package user.com.example.administrator.electronicproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sunbin on 2016/9/19.
 */
public class UserUtils {
    public static UserDao userDao;

    public static UserDao getDao(Context context){
        if (userDao == null){
            UserdbDaoMaster.DevOpenHelper openHelper = new UserdbDaoMaster.DevOpenHelper(context, "user");
            SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
            UserdbDaoMaster daoMaster = new UserdbDaoMaster(readableDatabase);
            UserdbDaoSession addressdbDaoSession = daoMaster.newSession();
            userDao = addressdbDaoSession.getUserDao();
        }
        return userDao;
    }
}
