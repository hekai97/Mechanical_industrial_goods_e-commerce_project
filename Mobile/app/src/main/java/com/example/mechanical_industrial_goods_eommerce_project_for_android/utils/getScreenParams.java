package com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.icu.text.DisplayContext;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class getScreenParams {
    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */

    public static int getScreenHeight(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);
        return dm.heightPixels;
    }
}
