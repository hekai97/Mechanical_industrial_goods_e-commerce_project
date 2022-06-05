package com.example.mechanical_industrial_goods_eommerce_project_for_android.config;

public class Constant {

    public static class API {

        //基地址
        public static final String BASE_URL = "http://192.168.43.74:8080/actionmall/";
//        public static final String BASE_URL = "http://192.168.43.74:8080/";
        //产品类型参数地址
        public static final String CATEGORY_PARAM_URL = BASE_URL + "param/findallparams";
//        public static final String CATEGORY_PARAM_URL = BASE_URL + "findallparams";
        //热销商品
        public static final String HOT_PRODUCT_URL = BASE_URL + "product/findhotproducts";
//        public static final String HOT_PRODUCT_URL = BASE_URL + "findhotproducts";
        //获取商品列表信息
        public static final String CATEGORY_PRODUCT_URL = BASE_URL + "product/findproducts";
//        public static final String CATEGORY_PRODUCT_URL = BASE_URL + "findproducts";
        //购物车列表
        public static final String  CART_LIST_URL = BASE_URL+"cart/findallcarts";
        //加入购物车
        public static final String CART_ADD_URL = BASE_URL+"cart/savecart";
        //更新购物中商品的数量
        public static final String CART_UPDATE_URL = BASE_URL+"cart/updatecarts";
        //删除购物车中商品
        public static final String CART_DEL_URL = BASE_URL+"cart/delcarts";
        //获取用户信息
        public static final String USER_INFO_URL = BASE_URL+"user/getuserinfo";
        //登陆接口
        public static final String USER_LOGIN_URL= BASE_URL+"user/do_login";

    }

    public static class ACTION {
        //加载购物车列表的
        public static final String LOAD_CART_ACTION="cn.techaction.mall.LOAD_CART_ACTION";

    }
}
