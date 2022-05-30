package com.example.mechanical_industrial_goods_eommerce_project_for_android.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 消息提示工具类
 */
public class ToastUtils {
    public static void showLongToast(Context context, CharSequence charSequence) {
        //应用context,显示的文本，消息框停留的市场
        Toast.makeText(context.getApplicationContext(), charSequence, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, CharSequence charSequence) {
        Toast.makeText(context.getApplicationContext(), charSequence, Toast.LENGTH_SHORT).show();
    }
}