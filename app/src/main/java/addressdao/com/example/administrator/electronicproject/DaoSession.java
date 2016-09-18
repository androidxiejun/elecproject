package addressdao.com.example.administrator.electronicproject;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig addressDaoConfig;

    private final AddressDao addressDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        addressDaoConfig = daoConfigMap.get(AddressDao.class).clone();
        addressDaoConfig.initIdentityScope(type);

        addressDao = new AddressDao(addressDaoConfig, this);

        registerDao(Address.class, addressDao);
    }
    
    public void clear() {
        addressDaoConfig.clearIdentityScope();
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }

}
