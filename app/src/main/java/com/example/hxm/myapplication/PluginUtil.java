package com.example.hxm.myapplication;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import dalvik.system.DexClassLoader;

/**
 * Created by August on 2017/3/18.
 */
public class PluginUtil {
    private static final String PLUGIN_NAME = "UserPlugin.apk";

    /**
     * 加载插件APP的类 * * @param context * @return * @throws IOException
     */
    public static IUserService load(Context context) throws IOException {
        String src = context.getFilesDir().getAbsolutePath() + File.separator + PLUGIN_NAME;
        // 插件APK存放的位置
        String dest = context.getFilesDir().getAbsolutePath() + File.separator + "plugin" + File.separator;
        // 插件APK转换为dex的文件夹, 也就是DexClassLoader加载的classPath
        // 创建dest目录
        File destFile = new File(dest);
        if (!destFile.exists()) {
            destFile.mkdir();
        } else if (!destFile.isDirectory()) {
            destFile.delete();
            destFile.mkdir();
        }
        try {
            copyPlugin(context, src);
            // 把插件从assets拷贝出来, 如果是从网络下载下来的 那么就可以从下载文件的路径拷贝过来
            DexClassLoader classLoader = new DexClassLoader(src, dest, null, context.getClassLoader());
            // 构造自己的DexClassLoader
            Class<?> cls = classLoader.loadClass("com.example.userplugin.impl.UserService");
            // 加载插件APK中的UserService类
            return (IUserService) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拷贝插件APP到插件目录 * * @param context * @param dest * @throws IOException
     */
    private static void copyPlugin(Context context, String dest) throws IOException {
        InputStream is = null;
        FileOutputStream fos = null;
        is = context.getAssets().open(PLUGIN_NAME);
        fos = new FileOutputStream(dest);
        int len;
        byte[] buf = new byte[1024];
        while ((len = is.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        is.close();
        fos.close();
    }
}

