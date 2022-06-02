package com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils;

import android.content.Context;

public class DpToPx {

    /**
     * dp转像素
     * @param context
     * @param dpValue
     * @return
     */
    public static int dpTopx(Context context,float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

}
