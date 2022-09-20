package cn.jx.snakegame;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 获取全家context
 */
public class MyApp extends Application {
    public static MyApp app;
    public static Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context=getApplicationContext();
        sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        editor = sp.edit();
    }

    public static Context getContext() {
        return context;
    }

    public static MyApp getApp() {
        return app;
    }

    public SharedPreferences getSp() {
        return sp;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }
}
