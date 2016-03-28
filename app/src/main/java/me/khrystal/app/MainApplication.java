package me.khrystal.app;

import android.app.Application;
import android.content.Context;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import me.khrystal.BuildConfig;
import me.khrystal.utils.Constants;

/**
 * usage: Application
 * author: kHRYSTAL
 * create time: 16/3/28
 * update time:
 * email: 723526676@qq.com
 */
public class MainApplication extends Application{
    private RefWatcher mRefWatcher;

    private static Context mApplicationContext;

    public static Context getInstance() {
        return mApplicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        init leancloud
        AVOSCloud.initialize(this, Constants.LEANCLOUD_KEY,Constants.LEANCLOUD_APPID);
//        init JPush
//        JPushInterface.setDebugMode(BuildConfig.DEBUG);
//        JPushInterface.init(this);
//if OOM refwatcher will toast notification;
        mRefWatcher = LeakCanary.install(this);
        mApplicationContext = this;
        KLog.init(BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * get Memory monitor
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context){
        MainApplication application = (MainApplication)context.getApplicationContext();
        return application.mRefWatcher;
    }
}
