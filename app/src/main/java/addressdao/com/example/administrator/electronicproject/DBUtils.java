package addressdao.com.example.administrator.electronicproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sunbin on 2016/9/18.
 */
public class DBUtils {

    public static AddressDao addressDao;

    public static AddressDao getDao(Context context){
        if (addressDao == null){
            DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, "android");
            SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
            DaoMaster daoMaster = new DaoMaster(readableDatabase);
            DaoSession daoSession = daoMaster.newSession();
            addressDao = daoSession.getAddressDao();
        }
        return addressDao;
    }
}
