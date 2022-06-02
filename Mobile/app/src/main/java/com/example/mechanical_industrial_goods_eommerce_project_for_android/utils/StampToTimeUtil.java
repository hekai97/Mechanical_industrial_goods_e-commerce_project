package com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StampToTimeUtil {

    //将时间戳转换为时间

    public static String stampToTime(String s) throws Exception{
        s = s + "000";//将10位时间戳转换为long类型13位时间戳
        Log.d("s",s);
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        //将时间戳转换为时间
        Date date = new Date(lt);
        Log.d("date",date+"");
        //将时间调整为yyyy-MM-dd HH:mm:ss时间样式
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToTimeHourMin(String s){
        //截取/之后字符串
        String str = null;
        try {
            str = stampToTime(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str1=str.substring(0, str.indexOf(" "));
        String str2=str.substring(str1.length()+1, str.length());
        Log.d("time",str2);
        return str2;
    }
    /**
     * 转换时间时区
     *
     * @param dateStr        需要转的时间字符串
     * @param sourceTimeZone 源时间时区 GMT+8
     * @param targetTimeZone 目标时间时区 GMT+6
     * @return
     * @throws ParseException
     */
    /*public static String converDateGMT(String dateStr, String sourceTimeZone, String targetTimeZone) throws ParseException {
        sourceTimeZone = StrUtil.isEmpty(sourceTimeZone) ? GMT_8 : sourceTimeZone;
        targetTimeZone = StrUtil.isEmpty(targetTimeZone) ? GMT_8 : targetTimeZone;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取传入的时间值
        Long time = new Date(sdf.parse(dateStr).getTime()).getTime();
        //获取源时区时间相对的GMT时间
        Long sourceRelativelyGMT = time - TimeZone.getTimeZone(sourceTimeZone).getRawOffset();
        //GMT时间+目标时间时区的偏移量获取目标时间
        Long targetTime = sourceRelativelyGMT + TimeZone.getTimeZone(targetTimeZone).getRawOffset();
        Date date = new Date(targetTime);
        return DateUtil.formatDateTime(date);
    }*/
}
