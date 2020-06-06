package com.just.agentweb;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Adapted from com.blankj.utilcode.util.ProcessUtils#getCurrentProcessName
 */
class ProcessUtils {

    static String getCurrentProcessName(Context context) {
        String name = getCurrentProcessNameByFile();
        if (!TextUtils.isEmpty(name)) return name;
        name = getCurrentProcessNameByAms(context);
        if (!TextUtils.isEmpty(name)) return name;
        name = getCurrentProcessNameByReflect(context);
        return name;
    }

    private static String getCurrentProcessNameByFile() {
        BufferedReader mBufferedReader =null;
        FileReader fr = null;
        String processName = "";
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            fr = new FileReader(file);
           mBufferedReader= new BufferedReader(fr);

           processName = mBufferedReader.readLine().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }finally {
            try {
                if (mBufferedReader != null) {
                    mBufferedReader.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return processName;

    }

    private static String getCurrentProcessNameByAms(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) return "";
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) return "";
        int pid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.pid == pid) {
                if (aInfo.processName != null) {
                    return aInfo.processName;
                }
            }
        }
        return "";
    }

    private static String getCurrentProcessNameByReflect(Context context) {
        String processName = "";
        try {
            Application app = (Application) context.getApplicationContext();
            Field loadedApkField = app.getClass().getField("mLoadedApk");
            loadedApkField.setAccessible(true);
            Object loadedApk = loadedApkField.get(app);

            Field activityThreadField = loadedApk.getClass().getDeclaredField("mActivityThread");
            activityThreadField.setAccessible(true);
            Object activityThread = activityThreadField.get(loadedApk);

            Method getProcessName = activityThread.getClass().getDeclaredMethod("getProcessName");
            processName = (String) getProcessName.invoke(activityThread);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processName;
    }

}
