package com.example.security;

import android.content.Context;
import android.content.res.AssetManager;

import com.blankj.utilcode.util.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Jcking
 * @time 2020-01-03 18:37
 */
public class LogManager {

    private static JSONObject logConfig = null;

    private static synchronized JSONObject getLogConfig() {
        if (logConfig == null) {
            String json = getJson(
                    ActivityUtils.getTopActivity(), "logconfig.json");
            try {
                logConfig = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (logConfig == null) {
            logConfig = new JSONObject();
        }
        return logConfig;
    }

    //读取方法
    private static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    public static String v() {
        JSONObject object = getLogConfig();
        return object.optString("v1") +
                object.optString("v2") +
                object.optString("v3") +
                object.optString("v4") +
                object.optString("v5");
    }

    public static String e() {
        JSONObject object = getLogConfig();
        return object.optString("d6") +
                object.optString("d7") +
                object.optString("d8") +
                object.optString("d9") +
                object.optString("e1") +
                object.optString("e2") +
                object.optString("e3") +
                object.optString("e4") +
                object.optString("e5") +
                object.optString("e6") +
                object.optString("e7") +
                object.optString("e8") +
                object.optString("e9") +
                object.optString("v0");
    }

    public static String w() {
        JSONObject object = getLogConfig();
        return object.optString("w2") +
                object.optString("w1");
    }

    public static String w(String tag) {
        JSONObject object = getLogConfig();
        return object.optString("w2") +
                object.optString("w0");
    }

    public static String i() {
        JSONObject object = getLogConfig();
        return object.optString("i1") +
                object.optString("i2") +
                object.optString("i3") +
                object.optString("i4");
    }

    public static String d() {
        JSONObject object = getLogConfig();
        return object.optString("i5") +
                object.optString("i5") +
                object.optString("i5") +
                object.optString("i5");
    }
}
