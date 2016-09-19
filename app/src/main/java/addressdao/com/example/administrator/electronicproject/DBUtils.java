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
            AddressdbDaoMaster.DevOpenHelper openHelper = new AddressdbDaoMaster.DevOpenHelper(context, "androidss");
            SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
            AddressdbDaoMaster daoMaster = new AddressdbDaoMaster(readableDatabase);
            AddressdbDaoSession addressdbDaoSession = daoMaster.newSession();
            addressDao = addressdbDaoSession.getAddressDao();
        }
        return addressDao;
    }
}
