package com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEmail {

    public boolean checkUserEmail(String email) {
        /*
         * 正则表达式 测试 邮箱是否合法
         */
        Pattern pattern = Pattern.compile(
                "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
