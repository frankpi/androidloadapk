package com.example.hxm.myapplication;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

class Utils {
    private Context mContext;
    private HashMap sMap = new HashMap();

    /**
     * 创建一个Entity保存APK的信息 * * @param apkPath * @return
     */
    private PluginApk createApk(String apkPath) {
        PluginApk pluginApk = null;
        try { // 事实就是跟前面那样动态加载资源的原理是一样的
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, apkPath);
            Resources resources = new Resources(assetManager, mContext.getResources().getDisplayMetrics(), mContext.getResources().getConfiguration());
            pluginApk = new PluginApk(resources);
            pluginApk.classLoader = createDexClassLoader(apkPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pluginApk;
    }

    private ClassLoader createDexClassLoader(String apkPath) {
        return new DexClassLoader(apkPath, new File(apkPath).getParent(), null, mContext.getClassLoader());
    }

    /**
     * 查询APK的包信息 * * @param apkPath * @return
     */
    private PackageInfo queryPackageInfo(String apkPath) {
        PackageManager packageManager = mContext.getPackageManager();
        return packageManager.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
    }

    /**
     * 加载并创建APK的信息 * * @param apkPath * @return
     */
    public PluginApk loadApk(String apkPath,Context context) {
        mContext = context;
        PackageInfo packageInfo = queryPackageInfo(apkPath);
        // 获取未安装的插件APK包信息
        if (packageInfo == null || TextUtils.isEmpty(packageInfo.packageName)) {
            return null;
        }
        PluginApk pluginApk = (PluginApk) sMap.get(packageInfo.packageName);
        // 从缓存中获取
        if (pluginApk == null) {
            pluginApk = createApk(apkPath); // 缓存中不存在, 则开始创建APK信息
            if (pluginApk != null) { // 缓存
                pluginApk.packageInfo = packageInfo;
                sMap.put(packageInfo.packageName, pluginApk);
            } else {
                throw new NullPointerException("plugin apk is null");
            }
        }
        return pluginApk;
    }

}