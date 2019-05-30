package com.example.nice.taobao_shop.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by ws on 2019/4/30.
 */

public class BaseApp extends Application {
    private static BaseApp sBaseApp;
    public static int mWidthPixels;
    public static int mHeightPixels;

    public static BaseApp getsBaseApp() {
        return sBaseApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sBaseApp = this;
        getScreenWH();
        packageContentIsCorrect(getPackageName() , this);
    }

    //计算屏幕宽高
    private void getScreenWH() {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
    }

    private static boolean packageContentIsCorrect(String filename, Context context) {
        PackageManager mPm = context.getPackageManager();
        PackageInfo pi = mPm.getPackageArchiveInfo(filename, PackageManager.GET_RECEIVERS);
        if (pi == null) {
            System.out.println("Package could not be parsed successfully.");
            return false;
        }
        if (!pi.packageName.equals("com.test")) {
            System.out.println("Package name in apk (" + pi.packageName + ") does not match package name specified by programmer (" + "com.test" + ").");
            return false;
        } //判断是否需要安装新版本
        try {
            int installVersionCode = pi.versionCode;
            int versionCode = mPm.getPackageInfo(context.getPackageName(), 0).versionCode;
            System.out.println("pi-installVersionCode----------------" + installVersionCode);
            System.out.println("pi--versionCode---------------" + pi.versionCode);
            if (installVersionCode <= versionCode) {
                System.out.println("Package not need update.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static BaseApp getInstance(){
        return sBaseApp;
    }

    public static Resources getRes() {
        return sBaseApp.getResources();
    }
}
