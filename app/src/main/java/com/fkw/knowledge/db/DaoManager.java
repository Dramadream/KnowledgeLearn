package com.fkw.knowledge.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fkw.knowledge.db.gen.DaoMaster;
import com.fkw.knowledge.db.gen.DaoSession;


/**
 * Author:          Kevin <BR/>
 * CreatedTime:     2018/12/5 16:28 <BR/>
 * Desc:            GreenDao 的管理类 <BR/>
 * <p/>
 * ModifyTime:      <BR/>
 * ModifyItems:     <BR/>
 *
 * @author: Kevin <BR/>
 */
public class DaoManager {

    private static final String TAG = DaoManager.class.getSimpleName();
    //创建数据库的名字
    private static final String DB_NAME = "1shuo_greendao.db";
    //初始化上下文
    private Context context;
    //多线程中要被共享的使用volatile关键字修饰  GreenDao管理类
    private volatile static DaoManager sInstance;
    //它里边实际上是保存数据库的对象
    private static DaoMaster sDaoMaster;
    //创建数据库的工具
    private static DaoMaster.DevOpenHelper sHelper;
    //管理gen里生成的所有的Dao对象里边带有基本的增删改查的方法
    private static DaoSession sDaoSession;

    /**
     * 单例模式获得操作数据库对象
     *
     * @return
     */
    public static DaoManager getInstance() {
        if (sInstance == null) {
            synchronized (DaoManager.class) {
                if (sInstance == null) {
                    sInstance = new DaoManager();
                }
            }
        }
        return sInstance;
    }

    private DaoManager() {
    }

    /**
     * 初始化上下文创建数据库的时候使用
     */
    public void init(Context context) {
        this.context = context;
    }

    /**
     * 判断是否有存在数据库，如果没有则创建
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (sDaoMaster == null) {
            // 创建数据库
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context, DB_NAME, null);
            //获取可写数据库
            SQLiteDatabase db = helper.getWritableDatabase();
            //获取数据库对象
            sDaoMaster = new DaoMaster(db);
        }
        return sDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (sDaoSession == null) {
            if (sDaoMaster == null) {
                sDaoMaster = getDaoMaster();
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (sHelper != null) {
            sHelper.close();
            sHelper = null;
        }
    }

    public void closeDaoSession() {
        if (sDaoSession != null) {
            sDaoSession.clear();
            sDaoSession = null;
        }
    }
}
